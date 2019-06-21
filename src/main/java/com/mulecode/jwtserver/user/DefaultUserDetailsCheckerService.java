package com.mulecode.jwtserver.user;

import com.mulecode.jwtserver.event.JwtServerEventPublisher;
import com.mulecode.jwtserver.event.JwtServerEventType;
import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.user.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static java.util.Objects.isNull;

public class DefaultUserDetailsCheckerService implements UserDetailsCheckerService {

    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder userPasswordEncoder;
    @Autowired
    private JwtServerEventPublisher eventPublisher;

    public void validate(AuthorizarionRequest tokenRequest, UserDetails loadedUser) throws UserDetailsException {

        if (isNull(loadedUser)) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CREDENTIAL_NOT_FOUND,
                    tokenRequest.getUserName()
            );

            throw new UserDetailsException("Invalid username or password.");
        }

        var isUserPasswordMatches = userPasswordEncoder.matches(
                tokenRequest.getUserPassword(),
                loadedUser.getPassword()
        );

        if (loadedUser.isDisabled()) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CREDENTIAL_DISABLED,
                    tokenRequest.getClientId(),
                    tokenRequest.getUserName()
            );

            throw new UserDetailsException("Account is disabled.");
        }

        if (loadedUser.isExpired()) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CREDENTIAL_EXPIRED,
                    tokenRequest.getClientId(),
                    tokenRequest.getUserName()
            );

            throw new UserDetailsException("Account is expired.");
        }

        if (loadedUser.isLocked()) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CREDENTIAL_LOCKED,
                    tokenRequest.getClientId(),
                    tokenRequest.getUserName()
            );

            throw new UserDetailsException("Account is locked.");
        }

        if (!isUserPasswordMatches) {

            eventPublisher.publishClientEvent(
                    JwtServerEventType.CREDENTIAL_INVALID_PASSWORD,
                    tokenRequest.getClientId(),
                    tokenRequest.getUserName()
            );

            throw new UserDetailsException("Invalid username or password.");
        }
    }
}
