package com.mulecode.jwtserver.user;

import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;
import com.mulecode.jwtserver.user.model.BaseUserDetails;
import com.mulecode.jwtserver.user.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userPasswordEncoder")
    PasswordEncoder userPasswordEncoder;

    @Override
    public UserDetails loadUser(AuthorizarionRequest request) throws UserDetailsException {

        if (!request.getUserName().equalsIgnoreCase("root")) {

            throw new UserDetailsException("Invalid username or password.");
        }

        var user = new BaseUserDetails();

        user.setUserId("1234");
        user.setUserName("root");
        user.setPassword(userPasswordEncoder.encode("root"));
        user.setDisabled(false);
        user.setExpired(false);
        user.setLocked(false);
        user.setAuthorities(List.of("ROLE_SAMPLEA", "ROLE_SAMPLEB"));

        user.setAdditionalInformation(Map.of());

        return user;
    }

}
