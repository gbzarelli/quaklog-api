package br.com.helpdev.quaklog.entity.vo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameUUIDTest {

    private static final String INVALID_UUID = "ABC";
    private static final String VALID_UUID = "83ad6f30-5372-44cf-86d4-3d9d3edc919b";

    @Test
    void shouldCreateGameUUIDWithValidUUID() {
        GameUUID gameUUID = GameUUID.of(UUID.fromString(VALID_UUID));
        assertEquals(VALID_UUID, gameUUID.toString());
    }

    @Test
    void shouldCreateGameUUIDWithRandomUUID() {
        GameUUID gameUUID = GameUUID.create();
        GameUUID gameUUID_2 = GameUUID.of(UUID.fromString(gameUUID.toString()));
        assertEquals(gameUUID.toString(), gameUUID_2.toString());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateGameUUIDWithInvalidUUID() {
        assertThrows(IllegalArgumentException.class, () -> GameUUID.of(UUID.fromString(INVALID_UUID)));
    }

}