package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void shouldBuildGameWithSuccess() {
        final PlayerInGame playerInGame1 = Mockito.mock(PlayerInGame.class);
        final PlayerInGame playerInGame = Mockito.mock(PlayerInGame.class);
        final World world = Mockito.mock(World.class);
        final Map<String, String> params = Collections.emptyMap();
        final GameTime endGameTime = GameTime.of("02:02");
        final GameUUID uuid = GameUUID.create();
        final LocalDate gameDate = LocalDate.now();
        Map<Integer, PlayerInGame> players = new HashMap<>();
        players.put(1, playerInGame1);

        final Game.GameBuilder gameBuilder = new Game.GameBuilder(GameTime.of("00:11"), gameDate);
        gameBuilder.addNotification("A");
        gameBuilder.setPlayers(players);
        gameBuilder.addPlayerInGame(playerInGame);
        gameBuilder.setWorld(world);
        gameBuilder.setTotalKills(10);
        gameBuilder.setGameParameters(params);
        gameBuilder.setEndGameTime(endGameTime);
        gameBuilder.setGameUUID(uuid);

        final Game game = gameBuilder.build();
        assertEquals(playerInGame, game.getPlayers().get(0));
        assertEquals(2, game.getPlayers().size());
        Assertions.assertEquals(endGameTime, game.getEndGameTime());
        Assertions.assertEquals(uuid, game.getGameUUID());
        assertEquals(1, game.getNotifications().size());
        assertEquals("A", game.getNotifications().get(0));
        assertEquals(10, game.getTotalKills().intValue());
        assertEquals(gameDate, game.getGameDate());
    }
}