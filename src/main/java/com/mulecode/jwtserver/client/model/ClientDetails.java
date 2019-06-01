package com.mulecode.jwtserver.client.model;

import java.util.Map;
import java.util.Set;

public interface ClientDetails {

    Set<String> getResourceIds();

    String getClientId();

    String getClientSecret();

    default Boolean isSecretRequired(){
        return true;
    }

    default Integer getAccessTokenValiditySeconds(){
        return 86400;
    }

    default Boolean invalidateOnReauthorization(){
        return true;
    }

    default Boolean isRefreshTokenEnabled() {
        return true;
    }

    default Integer getRefreshTokenValiditySeconds(){
        return 172800;
    }

    default Boolean isTokenPublicClaimVisible(){
        return true;
    }

    default Boolean isTokenPrivateClaimVisible(){
        return true;
    }

    String getTokenSignerMethod();

    String getTokenSignerSecret();

    String getTokenSignerPublicKey();

    String getTokenSignerPrivateKey();

    Map<String, Object> getAdditionalInformation();

}
