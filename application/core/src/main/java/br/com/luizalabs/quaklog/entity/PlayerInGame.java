package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.ConnectStatus;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.Mod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class PlayerInGame extends PlayerKiller implements CanDead {

    @Getter
    private final Integer id;
    @Getter
    private String name;

    private final List<Item> items;
    private final List<PlayerStatus> status;
    private Map<String, String> parameters;

    public PlayerInGame(GameTime time, Integer id) {
        this.id = id;
        this.items = new ArrayList<>();
        status = new ArrayList<>();
        connect(time);
    }

    @Builder
    public PlayerInGame(Integer kills, List<KillHistory> kdHistory, Integer id, String name, List<Item> items, List<PlayerStatus> status, Map<String, String> parameters) {
        super(kills,kdHistory);
        this.id = Objects.requireNonNull(id);
        this.status = Objects.requireNonNull(status);
        this.name = name;
        this.items = items == null ? Collections.emptyList() : items;
        this.parameters = parameters == null ? Collections.emptyMap() : parameters;
    }

    public List<PlayerStatus> getStatus() {
        return Collections.unmodifiableList(status);
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }


    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public ConnectStatus getConnectStatus() {
        return status.get(status.size() - 1).getStatus();
    }

    public void changeInfos(String playerName, Map<String, String> parameters) {
        this.name = playerName;
        this.parameters = parameters;
    }

    private void connect(GameTime time) {
        status.add(PlayerStatus.newConnectedTime(time));
    }

    public void begin(GameTime timeBegin) {
        status.add(PlayerStatus.newBeginTime(timeBegin));
    }

    public void disconnect(GameTime timeBegin) {
        status.add(PlayerStatus.newDisconnectedTime(timeBegin));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void deadBy(GameTime gameTime, Player player, Mod mod) {
        addHistory(KillHistory.deadBy(gameTime, player, mod));
        if (isWorld(player)) {
            decrementKill();
        }
    }

    private boolean isWorld(Player player) {
        return player.getId().equals(World.WORLD_ID);
    }

}
