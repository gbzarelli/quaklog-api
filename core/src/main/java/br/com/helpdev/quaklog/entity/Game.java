package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Game extends Notifiable {

    private final List<Player> players;
    private final Map<String, String> gameParameters;
    private final Integer totalKills;
    private final GameTime startGameTime;
    private final LocalDate gameDate;
    private final GameUUID gameUUID;
    private final World world;
    private final GameTime endGameTime;

    private Game(final GameBuilder builder) {
        this.players = List.copyOf(builder.players.values());
        this.gameParameters = Collections.unmodifiableMap(builder.gameParameters);
        this.totalKills = builder.totalKills.get();
        this.startGameTime = builder.startGameTime;
        this.gameDate = builder.gameDate;
        this.gameUUID = builder.gameUUID;
        this.world = builder.world;
        this.endGameTime = builder.endGameTime;
        this.addNotifiable(builder);
    }

    public Player getPlayerByID(final Integer id) {
        return players.stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
    }

    public static class GameBuilder extends Notifiable {
        private Map<Integer, PlayerInGame> players;
        private Map<String, String> gameParameters;
        private AtomicInteger totalKills;
        private GameTime startGameTime;
        private LocalDate gameDate;
        private GameUUID gameUUID;
        private World world;
        private GameTime endGameTime;

        public GameBuilder(final GameTime startGameTime, final LocalDate gameDate) {
            this.startGameTime = startGameTime;
            this.gameDate = gameDate;
            gameUUID = GameUUID.create();
            world = new World();
            totalKills = new AtomicInteger();
            gameParameters = new HashMap<>();
            players = new HashMap<>();
            addKillListener(world);
        }

        public Game build() {
            return new Game(this);
        }

        @Override
        public void addNotification(String notification) {
            super.addNotification(notification);
        }

        public GameBuilder addPlayerInGame(final PlayerInGame player) {
            if (players.containsKey(player.getId())) return this;
            players.put(player.getId(), player);
            addKillListener(player);
            return this;
        }

        private void addKillListener(PlayerKiller world) {
            world.setKillListener(killed -> incrementGameKill());
        }

        private void incrementGameKill() {
            totalKills.addAndGet(1);
        }

        public PlayerInGame getPlayerInGame(Integer id) {
            return players.get(id);
        }

        public PlayerKiller getPlayerKiller(Integer id) {
            if (id.equals(world.getId())) return world;
            return players.get(id);
        }

        public GameBuilder setGameParameters(Map<String, String> parameters) {
            gameParameters.clear();
            gameParameters.putAll(parameters);
            return this;
        }

        public GameBuilder setEndGameTime(GameTime endGameTime) {
            this.endGameTime = endGameTime;
            return this;
        }


        public GameBuilder setTotalKills(Integer totalKills) {
            this.totalKills.set(totalKills);
            return this;
        }


        public GameBuilder setGameUUID(GameUUID gameUUID) {
            this.gameUUID = gameUUID;
            return this;
        }

        public GameBuilder setPlayers(Map<Integer, PlayerInGame> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setWorld(World world) {
            this.world = world;
            return this;
        }

    }
}
