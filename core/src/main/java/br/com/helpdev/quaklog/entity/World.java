package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class World extends PlayerKiller {

    static final Integer WORLD_ID = 1022;
    static final String NAME = "<world>";

    private final List<Item> items;
    private final Map<String, String> parameters;
    private final List<PlayerStatus> status;

    World() {
        items = Collections.emptyList();
        parameters = Collections.emptyMap();
        status = getDefaultStatus();
    }

    @Builder
    World(final Integer kills, List<KillHistory> kdHistory,
          final List<Item> items,
          final Map<String, String> parameters,
          final List<PlayerStatus> status) {
        super(kills, kdHistory);
        this.items = items == null ? Collections.emptyList() : items;
        this.parameters = parameters == null ? Collections.emptyMap() : parameters;
        this.status = status == null ? getDefaultStatus() : status;
    }

    private List<PlayerStatus> getDefaultStatus() {
        return Collections.singletonList(PlayerStatus.newConnectedTime(GameTime.of("00:00")));
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
