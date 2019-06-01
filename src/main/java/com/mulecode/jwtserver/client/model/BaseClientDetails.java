package com.mulecode.jwtserver.client.model;

import java.util.Map;
import java.util.Set;

public class BaseClientDetails implements ClientDetails {

    private String clientId;
    private Set<String> resourceIds;
    private Boolean secretRequired;
    private String clientSecret;
    private Integer accessTokenValiditySeconds;
    private Boolean refreshTokenEnabled;
    private Boolean invalidateOnReauthorization;
    private Integer refreshTokenValiditySeconds;
    private String tokenSignerMethod;
    private String tokenSignerSecret;
    private String tokenSignerPublicKey;
    private String tokenSignerPrivateKey;
    private Boolean isTokenPrivateClaimVisible;
    private Boolean isTokenPublicClaimVisible;
    private Map<String, Object> additionalInformation;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public Boolean isSecretRequired() {
        return secretRequired;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Boolean isRefreshTokenEnabled() {
        return refreshTokenEnabled;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public String getTokenSignerMethod() {
        return tokenSignerMethod;
    }

    @Override
    public String getTokenSignerSecret() {
        return tokenSignerSecret;
    }

    @Override
    public String getTokenSignerPublicKey() {
        return tokenSignerPublicKey;
    }

    @Override
    public String getTokenSignerPrivateKey() {
        return tokenSignerPrivateKey;
    }

    @Override
    public Boolean invalidateOnReauthorization() {
        return invalidateOnReauthorization;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    @Override
    public Boolean isTokenPublicClaimVisible() {
        return isTokenPublicClaimVisible;
    }

    @Override
    public Boolean isTokenPrivateClaimVisible() {
        return isTokenPrivateClaimVisible;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setSecretRequired(Boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenEnabled(Boolean refreshTokenEnabled) {
        this.refreshTokenEnabled = refreshTokenEnabled;
    }

    public void setInvalidateOnReauthorization(Boolean invalidateOnReauthorization) {
        this.invalidateOnReauthorization = invalidateOnReauthorization;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setTokenSignerMethod(String tokenSignerMethod) {
        this.tokenSignerMethod = tokenSignerMethod;
    }

    public void setTokenSignerSecret(String tokenSignerSecret) {
        this.tokenSignerSecret = tokenSignerSecret;
    }

    public void setTokenSignerPublicKey(String tokenSignerPublicKey) {
        this.tokenSignerPublicKey = tokenSignerPublicKey;
    }

    public void setTokenSignerPrivateKey(String tokenSignerPrivateKey) {
        this.tokenSignerPrivateKey = tokenSignerPrivateKey;
    }

    public void setTokenPublicClaimVisible(Boolean tokenPublicClaimVisible) {
        isTokenPublicClaimVisible = tokenPublicClaimVisible;
    }

    public void setTokenPrivateClaimVisible(Boolean tokenPrivateClaimVisible) {
        isTokenPrivateClaimVisible = tokenPrivateClaimVisible;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
