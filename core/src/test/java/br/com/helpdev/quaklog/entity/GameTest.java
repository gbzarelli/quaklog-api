package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    void shouldBuildGameWithSuccess() {
        final var playerInGame1 = Mockito.mock(PlayerInGame.class);
        final var playerInGame = Mockito.mock(PlayerInGame.class);
        final var world = Mockito.mock(World.class);
        final Map<String, String> params = Collections.emptyMap();
        final var endGameTime = GameTime.of("02:02");
        final var uuid = GameUUID.create();
        final var gameDate = LocalDate.now();
        final var players = new HashMap<Integer, PlayerInGame>();
        players.put(1, playerInGame1);

        final var gameBuilder = new Game.GameBuilder(GameTime.of("00:11"), gameDate);
        gameBuilder.addNotification("A");
        gameBuilder.setPlayers(players);
        gameBuilder.addPlayerInGame(playerInGame);
        gameBuilder.setWorld(world);
        gameBuilder.setTotalKills(10);
        gameBuilder.setGameParameters(params);
        gameBuilder.setEndGameTime(endGameTime);
        gameBuilder.setGameUUID(uuid);

        final var game = gameBuilder.build();
        assertEquals(playerInGame, game.getPlayers().get(0));
        assertEquals(2, game.getPlayers().size());
        assertEquals(endGameTime, game.getEndGameTime());
        assertEquals(uuid, game.getGameUUID());
        assertEquals(1, game.getNotifications().size());
        assertEquals("A", game.getNotifications().get(0));
        assertEquals(10, game.getTotalKills().intValue());
        assertEquals(gameDate, game.getGameDate());
    }
}