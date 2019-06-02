package com.mulecode.jwtserver.flow;

import com.mulecode.jwtserver.client.ClientDetailsCheckerService;
import com.mulecode.jwtserver.client.ClientDetailsService;
import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.token.TokenService;
import com.mulecode.jwtserver.token.model.Token;
import com.mulecode.jwtserver.parser.TokenParser;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.store.StoreService;
import com.mulecode.jwtserver.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

public class RefreshFlowService implements FlowService {

    static final Logger LOGGER = LoggerFactory.getLogger(RefreshFlowService.class);

    @Autowired
    private StoreService tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("clientDetailsCheckerService")
    private ClientDetailsCheckerService clientDetailsCheckerService;

    @Autowired
    @Qualifier("jwtService")
    private TokenService jwtService;

    @Autowired
    @Qualifier("tokenParser")
    private TokenParser tokenParser;

    @Override
    public String name() {
        return "refresh_token";
    }

    @Override
    public Map<String, Object> process(AuthorizarionRequest tokenRequest) throws Exception {

        var clientLoaded = clientDetailsService.loadClientByClientId(
                tokenRequest.getClientId()
        );

        clientDetailsCheckerService.validate(
                tokenRequest,
                clientLoaded
        );

        jwtService.verify(
                tokenRequest.getOauthTokenRefresh(),
                clientLoaded
        );

        var refreshTokenParsed = JwtTokenUtils.parseToMap(
                tokenRequest.getOauthTokenRefresh()
        );

        var refreshTokenId = (String) refreshTokenParsed.get("jti");

        var accessTokenId = (String) refreshTokenParsed.get("ati");

        var accessTokenFound = tokenStore.find(accessTokenId)
                .orElseThrow(
                        () -> new Exception("Access token not found. Authentication required.")
                );

        if (!jwtService.isValid(tokenRequest.getOauthTokenRefresh())) {

            tokenStore.remove(accessTokenId);
            tokenStore.remove(refreshTokenId);
            throw new Exception("Refresh token is expired.");
        }

        var accessTokenParsed = JwtTokenUtils.parseToMap(
                accessTokenFound
        );

        var publicClamsCopied = JwtTokenUtils.extractPublicClams(accessTokenParsed);

        var token = jwtService.create(
                clientLoaded,
                publicClamsCopied
        );

        storeToken(
                clientLoaded,
                token
        );

        tokenStore.remove(accessTokenId);
        tokenStore.remove(refreshTokenId);

        return tokenParser.parse(
                clientLoaded,
                token
        );
    }

    private Map<String, Object> extractPrivateClams(Token token) throws Exception {

        var newAccessTokenParsed = JwtTokenUtils.parseToMap(token.getAccessToken());

        return JwtTokenUtils.extractPrivateClams(newAccessTokenParsed);
    }

    private void storeToken(ClientDetails loadedClient, Token token) {

        if (loadedClient.invalidateOnReauthorization()) {
            tokenStore.removeAllByClientId(
                    loadedClient.getClientId()
            );
        }

        tokenStore.store(
                token.getAccessTokenId(),
                loadedClient.getClientId(),
                token.getAccessTokenExpiresAt(),
                token.getAccessToken()
        );

        tokenStore.store(
                token.getRefreshTokenId(),
                loadedClient.getClientId(),
                token.getRefreshTokenExpiresAt(),
                token.getRefreshToken()
        );
    }
}
