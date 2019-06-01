package com.mulecode.jwtserver.client.settings;

import java.util.List;

public class ClientProperties {

    private List<ClientConfig> clients;

    public List<ClientConfig> getClients() {
        return clients;
    }

    public void setClients(List<ClientConfig> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "ClientProperties{" +
                "clients=" + clients +
                '}';
    }
}
