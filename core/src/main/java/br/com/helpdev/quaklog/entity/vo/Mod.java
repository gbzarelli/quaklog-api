package br.com.helpdev.quaklog.entity.vo;

import java.util.Arrays;

public enum Mod {
    MOD_SHOTGUN(1),
    MOD_MACHINEGUN(3),
    MOD_ROCKET(6),
    MOD_ROCKET_SPLASH(7),
    MOD_RAILGUN(10),
    MOD_BFG(12),
    MOD_BFG_SPLASH(13),
    MOD_FALLING(19),
    MOD_TRIGGER_HURT(22),
    NOT_FOUND(-1);

    public final Integer id;

    Mod(final Integer id) {
        this.id = id;
    }

    public static Mod byModID(final Integer killedModeID) {
        return Arrays.stream(values()).filter(it -> it.id.equals(killedModeID)).findFirst().orElse(NOT_FOUND);
    }
}
