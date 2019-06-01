package com.mulecode.jwtserver.token;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.token.model.Token;

import java.util.Map;

public interface TokenService {

    Token create(ClientDetails clientDetails, Map<String, Object> publicClams) throws Exception;

    void verify(String token, ClientDetails clientDetails) throws Exception;

    Boolean isValid(String token) throws Exception;

}
