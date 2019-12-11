package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;
import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class World extends PlayerKiller {

    static final Integer WORLD_ID = 1022;
    static final String NAME = "<world>";

    private final List<Item> items;
    private final Map<String, String> parameters;
    private final List<PlayerStatus> status;

    World() {
        items = Collections.emptyList();
        parameters = Collections.emptyMap();
        status = Collections.singletonList(PlayerStatus.newConnectedTime(GameTime.of("00:00")));
    }

    @Builder
    World(Integer kills, List<KillHistory> kdHistory, List<Item> items, Map<String, String> parameters, List<PlayerStatus> status) {
        super(new AtomicInteger(kills), kdHistory);
        this.items = items;
        this.parameters = parameters;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return WORLD_ID;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public List<PlayerStatus> getStatus() {
        return status;
    }
}
