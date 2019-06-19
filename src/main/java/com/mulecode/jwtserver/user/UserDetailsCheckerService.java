package com.mulecode.jwtserver.user;

import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.user.model.UserDetails;

public interface UserDetailsCheckerService {

    void validate(AuthorizarionRequest tokenRequest, UserDetails userDetails) throws UserDetailsException;
}
