package com.mulecode.jwtserver.resource.model;

public interface AuthorizarionRequest {

    String getGrantType();

    String getClientId();

    String getClientSecret();

    String getUserName();

    String getUserPassword();

    String getOauthTokenRefresh();

}
