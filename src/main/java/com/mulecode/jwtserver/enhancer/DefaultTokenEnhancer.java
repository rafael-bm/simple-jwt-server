package com.mulecode.jwtserver.enhancer;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.user.model.UserDetails;

import java.util.HashMap;
import java.util.Map;

public class DefaultTokenEnhancer implements TokenEnhancer {

    public Map<String, Object> enhance(ClientDetails clientDetails, UserDetails userDetails) {

        var enhancements = new HashMap<>(clientDetails.getAdditionalInformation());
        enhancements.putAll(userDetails.getAdditionalInformation());
        enhancements.put(getUserIdFieldName(), userDetails.getUserId());
        enhancements.put("user_name", userDetails.getUserName());
        enhancements.put("authorities", userDetails.getAuthorities());

        return enhancements;
    }

}
