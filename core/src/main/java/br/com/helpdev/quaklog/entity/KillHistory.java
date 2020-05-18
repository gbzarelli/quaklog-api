package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.KillMode;
import br.com.helpdev.quaklog.entity.vo.Mod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class KillHistory {

    static KillHistory killed(GameTime gameTime, Player player, Mod mod) {
        return new KillHistory(gameTime, KillMode.KILL, player.getId(), mod);
    }

    static KillHistory deadBy(GameTime gameTime, Player player, Mod mod) {
        return new KillHistory(gameTime, KillMode.DEAD, player.getId(), mod);
    }

    private final GameTime gameTime;
    private final KillMode killMode;
    private final Integer player;
    private final Mod mod;

    private KillHistory(GameTime gameTime, KillMode killMode, Integer player, Mod mod) {
        this.gameTime = gameTime;
        this.killMode = killMode;
        this.player = player;
        this.mod = mod;
    }

}
