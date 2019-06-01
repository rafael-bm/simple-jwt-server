package com.mulecode.jwtserver.resource;

public enum GrantType {

    PASSWORD("password"),
    REFRESH_TOKEN("refresh_roken");

    private final String headerValue;

    GrantType(String headerValue) {
        this.headerValue = headerValue;
    }

    public String getHeaderValue() {
        return headerValue;
    }
}
