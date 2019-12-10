package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;

import java.util.Collections;

public class WorldPlayer extends Player {

    static final Integer WORLD_ID = 1022;

    WorldPlayer() {
        super(new GameTime("00:00"), WORLD_ID);
        changeInfos("<world>", Collections.emptyMap());
    }


}
