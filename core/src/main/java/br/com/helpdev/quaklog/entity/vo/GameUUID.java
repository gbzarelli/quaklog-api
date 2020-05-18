package br.com.helpdev.quaklog.entity.vo;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class GameUUID {
    private final UUID uuid;

    public static GameUUID of(final String uuid) {
        return new GameUUID(uuid);
    }

    public static GameUUID create() {
        return new GameUUID();
    }

    private GameUUID(final String uuid) {
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
