package com.mulecode.jwtserver.resource.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenBodyRequest {

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
