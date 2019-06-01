package com.mulecode.jwtserver.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryStoreService implements StoreService {

    static final Logger LOGGER = LoggerFactory.getLogger(InMemoryStoreService.class);

    private Set<InMemoryTokenDto> store = new HashSet<>();
    private Set<InMemoryTokenDto> storeSync = Collections.synchronizedSet(store);

    @Override
    public void store(String key, String clientId, LocalDateTime expiresAt, String value) {

        var token = new InMemoryTokenDto();
        token.setId(key);
        token.setClientId(clientId);
        token.setExpiresAt(expiresAt);
        token.setValue(value);

        LOGGER.debug("store - token: {}", token);

        storeSync.add(token);
    }

    @Override
    public Optional<String> find(String key) {

        LOGGER.debug("find - id: {}", key);

        return storeSync.stream()
                .filter(token -> token.getId().equalsIgnoreCase(key))
                .map(InMemoryTokenDto::getValue)
                .findFirst();
    }

    @Override
    public void remove(String key) {

        LOGGER.debug("remove - id: {}", key);

        storeSync.removeIf(token -> token.getId().equalsIgnoreCase(key));

    }

    @Override
    public void removeAllByClientId(String clientId) {

        LOGGER.debug("removeAllByClientId - clientId: {}", clientId);

        storeSync.removeIf(token -> token.getClientId().equalsIgnoreCase(clientId));
    }
}
