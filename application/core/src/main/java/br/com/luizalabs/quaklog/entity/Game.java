package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Game extends Notifiable {

    private final GameUUID gameUUID;
    private final Map<Integer, Player> players;
    private final WorldPlayer world;
    private final GameTime startGameTime;
    private LocalDate importDate;
    private AtomicInteger totalKills;
    private Map<String, String> gameParameters;

    private Game(GameTime startGameTime) {
        this.startGameTime = startGameTime;
        gameUUID = new GameUUID();
        world = new WorldPlayer();
        addKillListener(world);
        players = new HashMap<>();
    }

    private void addPlayer(Player player) {
        if (players.containsKey(player.getId())) return;
        players.put(player.getId(), player);
        addKillListener(player);
    }

    private void addKillListener(Player world) {
        world.setKillListener(killed -> incrementGameKill());
    }

    private void incrementGameKill() {
        totalKills.addAndGet(1);
    }

    public Map<Integer, Player> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    public static class GameBuilder {
        private final Game game;

        public GameBuilder(GameTime startGameTime) {
            game = new Game(startGameTime);
        }

        public GameBuilder addNotification(String notification) {
            game.addNotification(notification);
            return this;
        }

        public GameBuilder importDate(LocalDate localDate) {
            game.importDate = localDate;
            return this;
        }

        public GameBuilder addPlayer(Player player) {
            game.addPlayer(player);
            return this;
        }

        public Player getPlayer(Integer id) {
            return game.players.get(id);
        }

        public Game build() {
            return game;
        }

        public GameBuilder gameParameters(Map<String, String> parameters) {
            game.gameParameters = parameters;
            return this;
        }
    }
}
