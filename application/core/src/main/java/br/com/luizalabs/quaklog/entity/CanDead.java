package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.Mod;

public interface CanDead {
    void deadBy(GameTime gameTime, Player player, Mod mod);
}
