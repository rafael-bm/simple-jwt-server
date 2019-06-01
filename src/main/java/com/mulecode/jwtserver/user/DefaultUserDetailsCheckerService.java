package com.mulecode.jwtserver.user;

import com.mulecode.jwtserver.client.ClientRegistrationException;
import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.user.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DefaultUserDetailsCheckerService implements UserDetailsCheckerService {

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder userPasswordEncoder;

    public Boolean validate(AuthorizarionRequest tokenRequest, UserDetails loadedUser) throws ClientRegistrationException {

        var isUserPasswordMatches = userPasswordEncoder.matches(
                tokenRequest.getUserPassword(),
                loadedUser.getPassword()
        );

        if (loadedUser.isDisabled()) {
            throw new UserDetailsException("Account is disabled.");
        }

        if (loadedUser.isExpired()) {
            throw new UserDetailsException("Account is expired.");
        }

        if (loadedUser.isLocked()) {
            throw new UserDetailsException("Account is locked.");
        }

        if (!isUserPasswordMatches) {

            throw new UserDetailsException("Invalid username or password.");
        }

        return true;
    }
}
