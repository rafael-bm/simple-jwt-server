package com.mulecode.jwtserver.user.model;

import java.util.Collection;
import java.util.Map;

public class BaseUserDetails implements UserDetails {

    private String userId;
    private String userName;
    private String password;
    private Collection<String> authorities;
    private Boolean locked;
    private Boolean expired;
    private Boolean disabled;
    private Map<String, Object> additionalInformation;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<String> getAuthorities() {
        return authorities;
    }

    @Override
    public Boolean isLocked() {
        return locked;
    }

    @Override
    public Boolean isExpired() {
        return expired;
    }

    @Override
    public Boolean isDisabled() {
        return disabled;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
