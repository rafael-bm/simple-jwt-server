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
import com.mulecode.jwtserver.user.UserDetailsCheckerService;
import com.mulecode.jwtserver.user.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

public class PasswordFlowService implements FlowService {

    static final Logger LOGGER = LoggerFactory.getLogger(PasswordFlowService.class);

    @Autowired
    private StoreService tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("jwtService")
    private TokenService jwtService;

    @Autowired
    @Qualifier("tokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    @Autowired
    @Qualifier("clientDetailsCheckerService")
    private ClientDetailsCheckerService clientDetailsCheckerService;

    @Autowired
    @Qualifier("userDetailsCheckerService")
    private UserDetailsCheckerService userDetailsCheckerService;

    @Autowired
    @Qualifier("tokenParser")
    private TokenParser tokenParser;

    @Autowired
    private JwtServerEventPublisher eventPublisher;

    @Override
    public String name() {
        return "password";
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

        var userLoaded = userDetailsService.loadUser(tokenRequest);

        userDetailsCheckerService.validate(
                tokenRequest,
                userLoaded
        );

        var publicClams = tokenEnhancer.enhance(
                clientLoaded,
                userLoaded
        );

        var userId = (String) publicClams.get(tokenEnhancer.getUserIdFieldName());

        var token = jwtService.create(
                clientLoaded,
                publicClams
        );

        storeToken(
                clientLoaded,
                userId,
                token
        );

        var tokenResponse = tokenParser.parse(
                clientLoaded,
                token
        );

        eventPublisher.publishClientEvent(
                JwtServerEventType.CREDENTIAL_SUCCESS_AUTHENTICATION,
                tokenRequest.getUserName()
        );

        return tokenResponse;
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
