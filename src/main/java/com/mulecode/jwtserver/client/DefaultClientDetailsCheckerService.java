package com.mulecode.jwtserver.client;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DefaultClientDetailsCheckerService implements ClientDetailsCheckerService {

    @Qualifier("clientPasswordEncoder")
    @Autowired
    private PasswordEncoder clientPasswordEncoder;

    static final Logger LOGGER = LoggerFactory.getLogger(DefaultClientDetailsCheckerService.class);

    @Override
    public Boolean validate(AuthorizarionRequest tokenRequest, ClientDetails loadedClient) throws ClientRegistrationException {

        if (!loadedClient.isSecretRequired()) {

            LOGGER.debug("loadClient - successfully loaded - Client: {}", loadedClient.getClientId());
            return true;
        }

        var isClientPasswordMatches = clientPasswordEncoder.matches(
                tokenRequest.getClientSecret(),
                loadedClient.getClientSecret()
        );

        if (!isClientPasswordMatches) {

            throw new ClientRegistrationException("Invalid client or secret for this server.");
        }

        LOGGER.debug("loadClient - successfully loaded - Client: {}", loadedClient.getClientId());
        return true;
    }
}
