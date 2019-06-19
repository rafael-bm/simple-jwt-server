package com.mulecode.jwtserver.client;

import com.mulecode.jwtserver.client.model.BaseClientDetails;
import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.client.settings.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static java.util.Objects.isNull;

public class DefaultClientDetailsService implements ClientDetailsService {

    static final Logger LOGGER = LoggerFactory.getLogger(DefaultClientDetailsService.class);

    @Autowired
    private ClientProperties clientProperties;

    @Override
    public ClientDetails loadClientByClientId(String clientName) {

        if (clientProperties.getClients().isEmpty()) {

            return null;
        }

        var clientFound = clientProperties.getClients()
                .stream()
                .filter(clientConfig -> clientConfig.getClientName().equalsIgnoreCase(clientName))
                .findFirst()
                .orElse(null);

        if (isNull(clientFound)) {

            return null;
        }

        var client = new BaseClientDetails();

        client.setResourceIds(Set.of(clientFound.getResourceIds().split(",")));
        client.setClientId(clientFound.getClientName());
        client.setClientSecret(clientFound.getClientSecret());

        client.setAccessTokenValiditySeconds(clientFound.getAccessTokenValiditySeconds());
        client.setRefreshTokenValiditySeconds(clientFound.getRefreshTokenValiditySeconds());
        client.setSecretRequired(clientFound.getSecretRequired());
        client.setRefreshTokenEnabled(clientFound.getRefreshTokenEnabled());

        client.setInvalidateOnReauthorization(clientFound.getInvalidateOnReauthorization());

        client.setTokenPrivateClaimVisible(clientFound.getTokenPrivateClaimVisible());
        client.setTokenPublicClaimVisible(clientFound.getTokenPublicClaimVisible());

        client.setTokenSignerMethod(clientFound.getTokenSignerMethod());

        client.setTokenSignerSecret(clientFound.getTokenSignerSecret());

        client.setTokenSignerPublicKey(clientFound.getTokenSignerPublicKey());
        client.setTokenSignerPrivateKey(clientFound.getTokenSignerPrivateKey());

        client.setAdditionalInformation(clientFound.getAdditionalInformation());

        return client;
    }

}
