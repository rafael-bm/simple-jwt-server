package com.mulecode.jwtserver.client.model;

import java.util.Map;
import java.util.Set;

public class MinClientDetails implements ClientDetails {

    private Set<String> resourceIds;
    private String clientId;
    private String clientSecret;
    private String tokenSignerMethod;
    private String tokenSignerSecret;
    private String tokenSignerPublicKey;
    private String tokenSignerPrivateKey;
    private Map<String, Object> additionalInformation;

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
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
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
