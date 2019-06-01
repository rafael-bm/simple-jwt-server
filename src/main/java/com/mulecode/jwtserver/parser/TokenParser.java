package com.mulecode.jwtserver.parser;

import com.mulecode.jwtserver.client.model.ClientDetails;
import com.mulecode.jwtserver.token.model.Token;

import java.util.Map;

public interface TokenParser {

    Map<String, Object> parse(ClientDetails clientDetails, Token token);
}
