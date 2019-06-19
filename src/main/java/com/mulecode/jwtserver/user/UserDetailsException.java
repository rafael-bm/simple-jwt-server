package com.mulecode.jwtserver.user;

public class UserDetailsException extends Exception {

    public UserDetailsException(String message) {
        super(message);
    }

    public UserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
