package com.mulecode.jwtserver.token.model;

import java.time.LocalDateTime;
import java.util.Map;

public interface Token {

    String getAccessTokenId();

    String getAccessToken();

    LocalDateTime getAccessTokenExpiresAt();

    String getRefreshTokenId();

    String getRefreshToken();

    LocalDateTime getRefreshTokenExpiresAt();

    Map<String, Object> getPublicClams();

    Map<String, Object> getPrivateClams();

}
