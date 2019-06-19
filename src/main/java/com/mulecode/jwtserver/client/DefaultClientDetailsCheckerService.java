package com.mulecode.jwtserver.client;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.event.JwtServerEventPublisher;
import com.mulecode.jwtserver.event.JwtServerEventType;
import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static java.util.Objects.isNull;

public class DefaultClientDetailsCheckerService implements ClientDetailsCheckerService {

    @Autowired
    @Qualifier("clientPasswordEncoder")
    private PasswordEncoder clientPasswordEncoder;
    @Autowired
    private JwtServerEventPublisher eventPublisher;

    static final Logger LOGGER = LoggerFactory.getLogger(DefaultClientDetailsCheckerService.class);

    @Override
    public void validate(AuthorizarionRequest tokenRequest, ClientDetails loadedClient) throws ClientRegistrationException {

        if (isNull(loadedClient)) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CLIENT_NOT_FOUND,
                    tokenRequest.getClientId()
            );

            throw new ClientRegistrationException("Invalid client or secret for this server.");
        }

        if (!loadedClient.isSecretRequired()) {

            LOGGER.debug("loadClient - successfully loaded - Client: {}", loadedClient.getClientId());
            return;
        }

        var isClientPasswordMatches = clientPasswordEncoder.matches(
                tokenRequest.getClientSecret(),
                loadedClient.getClientSecret()
        );

        if (!isClientPasswordMatches) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CLIENT_INVALID_SECRET,
                    tokenRequest.getClientId()
            );

            throw new ClientRegistrationException("Invalid client or secret for this server.");
        }

        LOGGER.debug("loadClient - successfully loaded - Client: {}", loadedClient.getClientId());
    }
}
