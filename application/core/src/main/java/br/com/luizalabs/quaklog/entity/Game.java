package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Game extends Notifiable {

    private final GameUUID gameUUID;
    private final Map<Integer, Player> players;
    private final WorldPlayer world;
    private final GameTime startGameTime;
    private final Map<String, String> gameParameters;
    private final AtomicInteger totalKills;
    private final LocalDate gameDate;
    private GameTime endGameTime;

    private Game(GameTime startGameTime, LocalDate gameDate) {
        this.startGameTime = startGameTime;
        this.gameDate = gameDate;
        gameUUID = new GameUUID();
        world = new WorldPlayer();
        totalKills = new AtomicInteger();
        gameParameters = new HashMap<>();
        players = new HashMap<>();
        addKillListener(world);
    }

    private void addKillListener(Player world) {
        world.setKillListener(killed -> incrementGameKill());
    }

    private void incrementGameKill() {
        totalKills.addAndGet(1);
    }

    public Map<String, String> getGameParameters() {
        return Collections.unmodifiableMap(gameParameters);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(players.values()));
    }

    public static class GameBuilder {
        private final Game game;

        public GameBuilder(GameTime gameTime, LocalDate gameDate) {
            game = new Game(gameTime, gameDate);
        }

        public GameBuilder addNotification(String notification) {
            game.addNotification(notification);
            return this;
        }

        public GameBuilder addPlayer(Player player) {
            if (game.players.containsKey(player.getId())) return this;
            game.players.put(player.getId(), player);
            game.addKillListener(player);
            return this;
        }

        public Player getPlayer(Integer id) {
            if (id.equals(game.world.getId())) return game.world;
            return game.players.get(id);
        }

        public Game build() {
            return game;
        }

        public GameBuilder setGameParameters(Map<String, String> parameters) {
            game.gameParameters.clear();
            game.gameParameters.putAll(parameters);
            return this;
        }

        public GameBuilder setEndGameTime(GameTime endGameTime) {
            game.endGameTime = endGameTime;
            return this;
        }


        public GameBuilder setTotalKills(Integer totalKills) {
            game.totalKills.set(totalKills);
            return this;
        }

    }
}
