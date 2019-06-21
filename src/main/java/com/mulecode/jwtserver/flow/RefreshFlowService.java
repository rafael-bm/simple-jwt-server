package com.mulecode.jwtserver.flow;

import com.mulecode.jwtserver.client.ClientDetailsCheckerService;
import com.mulecode.jwtserver.client.ClientDetailsService;
import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.enhancer.TokenEnhancer;
import com.mulecode.jwtserver.event.JwtServerEventPublisher;
import com.mulecode.jwtserver.event.JwtServerEventType;
import com.mulecode.jwtserver.parser.TokenParser;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.store.StoreService;
import com.mulecode.jwtserver.token.TokenService;
import com.mulecode.jwtserver.token.model.Token;
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
    @Qualifier("tokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    @Autowired
    @Qualifier("jwtService")
    private TokenService jwtService;

    @Autowired
    @Qualifier("tokenParser")
    private TokenParser tokenParser;

    @Autowired
    private JwtServerEventPublisher eventPublisher;

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

        var userId = (String) refreshTokenParsed.get(tokenEnhancer.getUserIdFieldName());

        var refreshTokenId = (String) refreshTokenParsed.get("jti");

        var accessTokenId = (String) refreshTokenParsed.get("ati");

        var accessTokenFound = tokenStore.find(accessTokenId);

        if (accessTokenFound.isEmpty()) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CLIENT_REFRESH_TOKEN_NOT_FOUND,
                    tokenRequest.getUserName()
            );

            throw new Exception("Access token not found. Authentication required.");
        }

        if (!jwtService.isValid(tokenRequest.getOauthTokenRefresh())) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CLIENT_INVALID_REFRESH_TOKEN,
                    tokenRequest.getUserName()
            );

            tokenStore.remove(accessTokenId);
            tokenStore.remove(refreshTokenId);
            throw new Exception("Refresh token is expired.");
        }

        var accessTokenParsed = JwtTokenUtils.parseToMap(
                accessTokenFound.get()
        );

        var publicClamsCopied = JwtTokenUtils.extractPublicClams(accessTokenParsed);

        var token = jwtService.create(
                clientLoaded,
                publicClamsCopied
        );

        storeToken(
                clientLoaded,
                userId,
                token
        );

        tokenStore.remove(accessTokenId);
        tokenStore.remove(refreshTokenId);

        var tokenResponse = tokenParser.parse(
                clientLoaded,
                token
        );

        eventPublisher.publishClientEvent(
                JwtServerEventType.CREDENTIAL_SUCCESS_REFRESH,
                tokenRequest.getClientId(),
                tokenRequest.getUserName()
        );

        return tokenResponse;
    }

    private Map<String, Object> extractPrivateClams(Token token) throws Exception {

        var newAccessTokenParsed = JwtTokenUtils.parseToMap(token.getAccessToken());

        return JwtTokenUtils.extractPrivateClams(newAccessTokenParsed);
    }

    private void storeToken(ClientDetails loadedClient, String userId, Token token) {

        if (loadedClient.invalidateOnReauthorization()) {
            tokenStore.removeAllByUserIdId(
                    userId
            );
        }

        tokenStore.store(
                token.getAccessTokenId(),
                userId,
                token.getAccessTokenExpiresAt(),
                token.getAccessToken()
        );

        tokenStore.store(
                token.getRefreshTokenId(),
                userId,
                token.getRefreshTokenExpiresAt(),
                token.getRefreshToken()
        );
    }
}
