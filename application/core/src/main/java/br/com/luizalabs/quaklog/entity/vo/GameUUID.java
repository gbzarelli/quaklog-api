package br.com.luizalabs.quaklog.entity.vo;

import java.util.UUID;

public class GameUUID {
    private final UUID uuid;

    public static GameUUID of(String uuid) {
        return new GameUUID(uuid);
    }

    public static GameUUID create() {
        return new GameUUID();
    }

    private GameUUID(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    private GameUUID() {
        this.uuid = java.util.UUID.randomUUID();
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
