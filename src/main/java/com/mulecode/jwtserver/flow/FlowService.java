package com.mulecode.jwtserver.flow;

import com.mulecode.jwtserver.resource.model.AuthorizarionRequest;

import java.util.Map;

public interface FlowService {

    String name();

    Map<String, Object> process(AuthorizarionRequest tokenRequest) throws Exception;
}
