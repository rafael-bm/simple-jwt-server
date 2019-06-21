package com.mulecode.jwtserver.event;

import org.springframework.context.ApplicationEvent;

public class JwtServerEvent extends ApplicationEvent {

    private JwtServerEventType eventType;
    private String clientName;
    private String credentialName;

    public JwtServerEvent(Object source, JwtServerEventType eventType, String clientName, String credentialName) {
        super(source);
        this.clientName = clientName;
        this.credentialName = credentialName;
        this.eventType = eventType;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCredentialName() {
        return credentialName;
    }

    public JwtServerEventType getEventType() {
        return eventType;
    }
}
