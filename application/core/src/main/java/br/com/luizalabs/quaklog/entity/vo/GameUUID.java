package br.com.luizalabs.quaklog.entity.vo;

import java.util.UUID;

public class GameUUID {
    private final UUID uuid;

    public GameUUID(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public GameUUID() {
        this.uuid = java.util.UUID.randomUUID();
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
