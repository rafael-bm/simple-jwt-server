package com.mulecode.jwtserver.event;

import org.springframework.context.ApplicationEvent;

public class JwtServerEvent extends ApplicationEvent {

    private JwtServerEventType eventType;
    private String message;

    public JwtServerEvent(Object source, JwtServerEventType eventType, String message) {
        super(source);
        this.message = message;
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public JwtServerEventType getEventType() {
        return eventType;
    }
}
