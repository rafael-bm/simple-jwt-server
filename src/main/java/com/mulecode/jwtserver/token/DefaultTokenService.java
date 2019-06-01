package com.mulecode.jwtserver.token;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.token.model.BaseToken;
import com.mulecode.jwtserver.token.model.Token;
import com.mulecode.jwtserver.utils.JwtTokenUtils;
import com.mulecode.jwtserver.utils.RsaKeyUtils;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class DefaultTokenService implements TokenService {

    @Override
    public Token create(ClientDetails clientDetails,
                        Map<String, Object> publicClams) throws Exception {

        var expirationTime = LocalDateTime.now().plusSeconds(
                clientDetails.getAccessTokenValiditySeconds()
        );

        var accessToken = JwtTokenUtils.createToken(
                new ArrayList<>(clientDetails.getResourceIds()),
                expirationTime,
                null,
                publicClams
        );

        var accessTokenSigned = signToken(
                accessToken,
                clientDetails
        );

        var token = new BaseToken();

        token.setAccessTokenId(accessToken.getJWTID());
        token.setAccessToken(JwtTokenUtils.serializeKey(accessTokenSigned));
        token.setAccessTokenExpiresAt(expirationTime);

        token.setPublicClams(publicClams);
        token.setPrivateClams(JwtTokenUtils.extractPrivateClams(
                JwtTokenUtils.parseToMap(token.getAccessToken())
        ));

        if (!clientDetails.isRefreshTokenEnabled()) {

            return token;
        }

        var expirationRefreshTime = LocalDateTime.now().plusSeconds(
                clientDetails.getRefreshTokenValiditySeconds()
        );

        var refreshToken = JwtTokenUtils.createToken(
                new ArrayList<>(clientDetails.getResourceIds()),
                expirationRefreshTime,
                accessToken.getJWTID(),
                null
        );

        var refreshTokenSigned = signToken(
                refreshToken,
                clientDetails
        );

        token.setRefreshTokenId(refreshToken.getJWTID());
        token.setRefreshToken(JwtTokenUtils.serializeKey(refreshTokenSigned));
        token.setRefreshTokenExpiresAt(expirationRefreshTime);


        return token;
    }

    @Override
    public void verify(String token, ClientDetails clientDetails) throws Exception {

        if (clientDetails.getTokenSignerMethod().equalsIgnoreCase("mac")) {

            var isMacSignValid = JwtTokenUtils.verifySignatureMac(
                    token,
                    clientDetails.getTokenSignerSecret()
            );

            if (!isMacSignValid) {

                throw new Exception("Token signature not valid");
            }

            return;
        }
        if (clientDetails.getTokenSignerMethod().equalsIgnoreCase("rsa")) {

            final RSAKey rsaKey = parseToRsaKey(clientDetails);

            var isRsaSignValid = JwtTokenUtils.verifySignatureRSA(
                    token,
                    rsaKey
            );

            if (!isRsaSignValid) {

                throw new Exception("Token signature not valid");
            }
            return;
        }

        throw new Exception("no valid signature method found");
    }

    @Override
    public Boolean isValid(String token) throws Exception {

        return JwtTokenUtils.isValid(token);
    }

    private SignedJWT signToken(JWTClaimsSet claimsSet,
                                ClientDetails clientDetails) throws Exception {

        if (clientDetails.getTokenSignerMethod().equalsIgnoreCase("mac")) {

            return JwtTokenUtils.signWithMac(
                    claimsSet,
                    clientDetails.getTokenSignerSecret()
            );
        }
        if (clientDetails.getTokenSignerMethod().equalsIgnoreCase("rsa")) {

            final RSAKey rsaKey = parseToRsaKey(clientDetails);


            return JwtTokenUtils.signWithRSA(
                    claimsSet,
                    rsaKey
            );
        }

        return null;
    }

    private RSAKey parseToRsaKey(ClientDetails clientDetails) throws Exception {

        final RSAPublicKey rsaPublicKey = RsaKeyUtils.parseToRSAPublic(
                clientDetails.getTokenSignerPublicKey()
        );
        final RSAPrivateKey privateKey = RsaKeyUtils.parseToRSAPrivate(
                clientDetails.getTokenSignerPrivateKey()
        );

        return new RSAKey.Builder(rsaPublicKey)
                .privateKey(privateKey)
                .build();
    }
}
