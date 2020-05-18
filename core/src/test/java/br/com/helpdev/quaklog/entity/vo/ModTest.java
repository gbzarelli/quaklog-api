package br.com.helpdev.quaklog.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModTest {
    @Test
    void shouldCreateModByModID() {
        Mod mod = Mod.byModID(13);
        assertEquals(Mod.MOD_BFG_SPLASH, mod);
    }

    @Test
    void shouldCreateTheNotFoundModWhenModIDNotFound() {
        Mod mod = Mod.byModID(1300);
        assertEquals(Mod.NOT_FOUND, mod);
    }
}