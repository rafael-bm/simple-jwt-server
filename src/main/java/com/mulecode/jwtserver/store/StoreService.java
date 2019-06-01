package com.mulecode.jwtserver.store;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StoreService {

    void store(String key, String clientId, LocalDateTime expiresAt, String token);

    Optional<String> find(String key);

    void remove(String key);

    void removeAllByClientId(String clientId);

}
