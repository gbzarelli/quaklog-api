package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class World extends PlayerKiller {

    static final Integer WORLD_ID = 1022;

    World() {
    }

    @Override
    public Integer getId() {
        return WORLD_ID;
    }

    @Override
    public List<Item> getItems() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "<world>";
    }

    @Override
    public Map<String, String> getParameters() {
        return Collections.emptyMap();
    }

    @Override
    public List<PlayerStatus> getStatus() {
        return Collections.singletonList(PlayerStatus.newConnectedTime(GameTime.of("00:00")));
    }
}
