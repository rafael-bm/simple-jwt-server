package com.mulecode.jwtserver.user;

import com.mulecode.jwtserver.client.ClientRegistrationException;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.user.model.UserDetails;

public interface UserDetailsCheckerService {

    Boolean validate(AuthorizarionRequest tokenRequest, UserDetails userDetails) throws ClientRegistrationException;
}
