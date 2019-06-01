package com.mulecode.jwtserver.enhancer;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.user.model.UserDetails;

import java.util.Map;

public interface TokenEnhancer {

    Map<String, Object> enhance(ClientDetails clientDetails, UserDetails userDetails);

}
