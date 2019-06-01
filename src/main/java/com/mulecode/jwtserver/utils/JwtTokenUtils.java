package com.mulecode.jwtserver.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JwtTokenUtils {

    public static final Set<String> privateJtwClaims = Set.of(
            "iss",
            "sub",
            "aud",
            "exp",
            "nbf",
            "iat",
            "jti",
            "ati"
    );

    public static final Predicate<String> isPrivateClaims = privateJtwClaims::contains;

    public static final Predicate<String> isNotPrivateClaims = isPrivateClaims.negate();

    public static JWTClaimsSet createToken(List<String> resourceIds,
                                           LocalDateTime expirationTime,
                                           String ati,
                                           Map<String, Object> publicClams) {

        var claimsSetBuilder = new JWTClaimsSet.Builder()
                .audience(resourceIds)
                .issueTime(new Date())
                .expirationTime(
                        Date.from(
                                expirationTime.toInstant(ZoneOffset.UTC)
                        )
                ).jwtID(UUID.randomUUID().toString());

        if (Objects.nonNull(ati)) {
            claimsSetBuilder.claim("ati", ati);
        }

        if (Objects.nonNull(publicClams)) {
            publicClams.forEach(claimsSetBuilder::claim);
        }

        return claimsSetBuilder.build();
    }

    public static Map<String, Object> extractPublicClams(Map<String, Object> token) {

        return token.entrySet().stream()
                .filter(entry -> JwtTokenUtils.isNotPrivateClaims.test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> extractPrivateClams(Map<String, Object> token) {

        return token.entrySet().stream()
                .filter(entry -> JwtTokenUtils.isPrivateClaims.test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Date toDate(LocalDateTime localDateTime) {

        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public static Map<String, Object> parseToMap(String token) throws Exception {

        var signedToken = JwtTokenUtils.parse(token);

        return signedToken.getJWTClaimsSet().getClaims();
    }

    public static SignedJWT parse(String token) throws Exception {

        return SignedJWT.parse(token);
    }

    public static Boolean isValid(String token) throws Exception {

        var parsedToken = parseToMap(token);

        var expireAt = (Date) parsedToken.get("exp");

        if (expireAt.before(new Date())) {

            return false;
        }

        return true;
    }

    public static Boolean verifySignatureMac(String token, String secret) throws Exception {

        SignedJWT signedJWT = parse(token);

        JWSVerifier verifier = new MACVerifier(secret);

        return signedJWT.verify(verifier);
    }

    public static SignedJWT signWithRSA(JWTClaimsSet claimsSet, RSAKey rsaJWK) throws Exception {

        JWSSigner signer = new RSASSASigner(rsaJWK);

        var header = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);

        signedJWT.sign(signer);

        return signedJWT;
    }

    public static Boolean verifySignatureRSA(String token, RSAKey rsaPublicJWK) throws Exception {

        SignedJWT signedJWT = parse(token);

        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);

        return signedJWT.verify(verifier);
    }

    public static String serializeKey(SignedJWT signedJWT) {

        return signedJWT.serialize();
    }

    public static SignedJWT signWithMac(JWTClaimsSet claimsSet, String secret) throws JOSEException {

        var header = new JWSHeader(JWSAlgorithm.HS256);

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        JWSSigner signer = new MACSigner(secret);
        signedJWT.sign(signer);

        return signedJWT;
    }
}
