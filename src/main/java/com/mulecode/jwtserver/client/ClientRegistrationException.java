package com.mulecode.jwtserver.client;

public class ClientRegistrationException extends Exception {

    public ClientRegistrationException(String message) {
        super(message);
    }

    public ClientRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
