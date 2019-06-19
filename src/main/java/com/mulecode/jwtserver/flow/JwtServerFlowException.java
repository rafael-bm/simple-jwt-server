package com.mulecode.jwtserver.flow;

public class JwtServerFlowException extends Exception {

    public JwtServerFlowException(String message) {
        super(message);
    }

    public JwtServerFlowException(String message, Throwable cause) {
        super(message, cause);
    }
}
