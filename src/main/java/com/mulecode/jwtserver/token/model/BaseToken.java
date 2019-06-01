package com.mulecode.jwtserver.token.model;

import java.time.LocalDateTime;
import java.util.Map;

public class BaseToken implements Token {

    private String accessTokenId;
    private String accessToken;
    private LocalDateTime accessTokenExpiresAt;
    private String refreshTokenId;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiresAt;
    private Map<String, Object> publicClams;
    private Map<String, Object> privateClams;

    @Override
    public String getAccessTokenId() {
        return accessTokenId;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public LocalDateTime getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    @Override
    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public LocalDateTime getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    @Override
    public Map<String, Object> getPublicClams() {
        return publicClams;
    }

    @Override
    public Map<String, Object> getPrivateClams() {
        return privateClams;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setAccessTokenId(String accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public void setAccessTokenExpiresAt(LocalDateTime accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public void setRefreshTokenExpiresAt(LocalDateTime refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }

    public void setPublicClams(Map<String, Object> publicClams) {
        this.publicClams = publicClams;
    }

    public void setPrivateClams(Map<String, Object> privateClams) {
        this.privateClams = privateClams;
    }
}
