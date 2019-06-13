package com.mulecode.jwtserver.store;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InMemoryTokenDto implements Serializable {

    private String id;
    private String userId;
    private LocalDateTime expiresAt;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InMemoryTokenDto{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", expiresAt=" + expiresAt +
                ", value='" + value + '\'' +
                '}';
    }
}
