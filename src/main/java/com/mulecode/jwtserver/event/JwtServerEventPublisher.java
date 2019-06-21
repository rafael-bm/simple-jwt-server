package com.mulecode.jwtserver.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JwtServerEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    Environment environment;

    public void publishClientEvent(JwtServerEventType eventType, String clientName) {

        publishClientEvent(eventType, clientName, null);
    }

    public void publishClientEvent(JwtServerEventType eventType, String clientName, String credentialName) {

        var eventBusEnabled = environment.getProperty(
                "spring.jwtserver.eventBus.enabled",
                Boolean.class,
                false
        );

        if (!eventBusEnabled) {
            return;
        }

        var event = new JwtServerEvent(
                this,
                eventType,
                clientName,
                credentialName
        );

        applicationEventPublisher.publishEvent(event);
    }
}
