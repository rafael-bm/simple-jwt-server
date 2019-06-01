package com.mulecode.jwtserver.client.settings;

import java.util.Map;

public class ClientConfig {

    private String clientName;
    private String clientSecret;
    private String resourceIds;
    private Integer accessTokenValiditySeconds = 60;
    private Integer refreshTokenValiditySeconds = 120;
    private Boolean secretRequired = true;
    private Boolean refreshTokenEnabled = true;
    private Boolean tokenPrivateClaimVisible = true;
    private Boolean tokenPublicClaimVisible = true;
    private Boolean invalidateOnReauthorization = true;
    private String tokenSignerMethod = "mac";
    private String tokenSignerSecret;
    private String tokenSignerPublicKey;
    private String tokenSignerPrivateKey;
    private Map<String, Object> additionalInformation;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public Boolean getSecretRequired() {
        return secretRequired;
    }

    public void setSecretRequired(Boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public Boolean getRefreshTokenEnabled() {
        return refreshTokenEnabled;
    }

    public void setRefreshTokenEnabled(Boolean refreshTokenEnabled) {
        this.refreshTokenEnabled = refreshTokenEnabled;
    }

    public Boolean getTokenPrivateClaimVisible() {
        return tokenPrivateClaimVisible;
    }

    public void setTokenPrivateClaimVisible(Boolean tokenPrivateClaimVisible) {
        this.tokenPrivateClaimVisible = tokenPrivateClaimVisible;
    }

    public Boolean getTokenPublicClaimVisible() {
        return tokenPublicClaimVisible;
    }

    public void setTokenPublicClaimVisible(Boolean tokenPublicClaimVisible) {
        this.tokenPublicClaimVisible = tokenPublicClaimVisible;
    }

    public Boolean getInvalidateOnReauthorization() {
        return invalidateOnReauthorization;
    }

    public void setInvalidateOnReauthorization(Boolean invalidateOnReauthorization) {
        this.invalidateOnReauthorization = invalidateOnReauthorization;
    }

    public String getTokenSignerMethod() {
        return tokenSignerMethod;
    }

    public void setTokenSignerMethod(String tokenSignerMethod) {
        this.tokenSignerMethod = tokenSignerMethod;
    }

    public String getTokenSignerSecret() {
        return tokenSignerSecret;
    }

    public void setTokenSignerSecret(String tokenSignerSecret) {
        this.tokenSignerSecret = tokenSignerSecret;
    }

    public String getTokenSignerPublicKey() {
        return tokenSignerPublicKey;
    }

    public void setTokenSignerPublicKey(String tokenSignerPublicKey) {
        this.tokenSignerPublicKey = tokenSignerPublicKey;
    }

    public String getTokenSignerPrivateKey() {
        return tokenSignerPrivateKey;
    }

    public void setTokenSignerPrivateKey(String tokenSignerPrivateKey) {
        this.tokenSignerPrivateKey = tokenSignerPrivateKey;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
