package com.mulecode.jwtserver.store;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StoreService {

    void store(String key, String userId, LocalDateTime expiresAt, String token);

    Optional<String> find(String key);

    void remove(String key);

    void removeAllByUserIdId(String userId);

}
