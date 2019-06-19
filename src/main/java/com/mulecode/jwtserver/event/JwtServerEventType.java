package com.mulecode.jwtserver.event;

public enum JwtServerEventType {

    CREDENTIAL_SUCCESS_AUTHENTICATION,
    CREDENTIAL_SUCCESS_REFRESH,

    CREDENTIAL_DISABLED,
    CREDENTIAL_EXPIRED,
    CREDENTIAL_LOCKED,
    CREDENTIAL_NOT_FOUND,
    CREDENTIAL_INVALID_PASSWORD,

    CLIENT_NOT_FOUND,
    CLIENT_INVALID_SECRET,
    CLIENT_INVALID_REFRESH_TOKEN,
    CLIENT_REFRESH_TOKEN_NOT_FOUND
}