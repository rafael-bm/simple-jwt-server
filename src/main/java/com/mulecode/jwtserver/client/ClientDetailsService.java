package com.mulecode.jwtserver.client;

import com.mulecode.jwtserver.client.model.ClientDetails;

public interface ClientDetailsService {

    ClientDetails loadClientByClientId(String clientName);

}
