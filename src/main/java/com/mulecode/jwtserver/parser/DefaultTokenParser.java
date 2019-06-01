package com.mulecode.jwtserver.parser;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.token.model.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultTokenParser implements TokenParser {

    private static final Logger LOGGER = LogManager.getLogger(DefaultTokenParser.class);

    @Override
    public Map<String, Object> parse(ClientDetails clientDetails,
                                     Token token) {

        var response = new HashMap<String, Object>();
        response.put("access_token", token.getAccessToken());
        response.put("expires_in", clientDetails.getAccessTokenValiditySeconds());
        response.put("refresh_token", token.getRefreshToken());

        if (clientDetails.isTokenPublicClaimVisible() && Objects.nonNull(token.getPublicClams())) {

            LOGGER.debug("parsing with public clams: {}", token.getPublicClams());
            token.getPublicClams().forEach(response::put);
        }

        if (clientDetails.isTokenPrivateClaimVisible() && Objects.nonNull(token.getPrivateClams())) {

            LOGGER.debug("parsing with private clams: {}", token.getPrivateClams());
            token.getPrivateClams().forEach(response::put);
        }

        return response;
    }
}
