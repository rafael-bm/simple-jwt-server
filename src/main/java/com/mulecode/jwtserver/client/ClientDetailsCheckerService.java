package com.mulecode.jwtserver.client;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;

public interface ClientDetailsCheckerService {

    void validate(AuthorizarionRequest tokenRequest, ClientDetails clientDetails) throws ClientRegistrationException;

}
