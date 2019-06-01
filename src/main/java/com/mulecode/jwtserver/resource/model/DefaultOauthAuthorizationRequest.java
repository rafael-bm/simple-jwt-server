package com.mulecode.jwtserver.resource.model;

public class DefaultOauthAuthorizationRequest implements AuthorizarionRequest {

    private String grantType;
    private String clientId;
    private String clientSecret;
    private String userName;
    private String userPassword;
    private String oauthTokenRefresh;

    @Override
    public String getGrantType() {
        return grantType;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String getOauthTokenRefresh() {
        return oauthTokenRefresh;
    }


    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setOauthTokenRefresh(String oauthTokenRefresh) {
        this.oauthTokenRefresh = oauthTokenRefresh;
    }
}
