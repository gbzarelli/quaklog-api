package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
public class Game extends Notifiable {

    private final List<Player> players;
    private final Map<String, String> gameParameters;
    private final Integer totalKills;
    private final GameTime startGameTime;
    private final LocalDate gameDate;
    private final GameUUID gameUUID;
    private final World world;
    private final GameTime endGameTime;

    public static class GameBuilder extends Notifiable {
        private Map<Integer, PlayerInGame> players;
        private Map<String, String> gameParameters;
        private AtomicInteger totalKills;
        private GameTime startGameTime;
        private LocalDate gameDate;
        private GameUUID gameUUID;
        private World world;
        private GameTime endGameTime;

        public GameBuilder(GameTime startGameTime, LocalDate gameDate) {
            this.startGameTime = startGameTime;
            this.gameDate = gameDate;
            gameUUID = GameUUID.create();
            world = new World();
            totalKills = new AtomicInteger();
            gameParameters = new HashMap<>();
            players = new HashMap<>();
            addKillListener(world);
        }

        @Override
        public void addNotification(String notification) {
            super.addNotification(notification);
        }

        public GameBuilder addPlayerInGame(PlayerInGame player) {
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

        public Game build() {
            val game = new Game(
                    Collections.unmodifiableList(new ArrayList<>(players.values())),
                    Collections.unmodifiableMap(gameParameters),
                    totalKills.get(),
                    startGameTime,
                    gameDate,
                    gameUUID,
                    world,
                    endGameTime
            );
            game.addNotifiable(this);
            return game;
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
