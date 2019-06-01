package com.mulecode.jwtserver.user.model;

import java.util.Collection;
import java.util.Map;

public interface UserDetails {

    String getUserId();

    String getUserName();

    String getPassword();

    Collection<String> getAuthorities();

    Boolean isLocked();

    Boolean isExpired();

    Boolean isDisabled();

    Map<String, Object> getAdditionalInformation();
}
