package br.com.luizalabs.quaklog.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUUIDTest {

    private static final String INVALID_UUID = "ABC";
    private static final String VALID_UUID = "83ad6f30-5372-44cf-86d4-3d9d3edc919b";

    @Test
    void createGameUUIDWithValidUUID() {
        GameUUID gameUUID = new GameUUID(VALID_UUID);
        assertEquals(VALID_UUID, gameUUID.toString());
    }

    @Test
    void createGameUUIDWithRandomUUID() {
        GameUUID gameUUID = new GameUUID();
        GameUUID gameUUID_2 = new GameUUID(gameUUID.toString());
        assertEquals(gameUUID.toString(), gameUUID_2.toString());
    }

    @Test
    void createGameUUIDWithFailed() {
        assertThrows(IllegalArgumentException.class, () -> new GameUUID(INVALID_UUID));
    }

}