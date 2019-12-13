package br.com.luizalabs.quaklog.entrypoint.rest;

import br.com.luizalabs.quaklog.entity.*;
import br.com.luizalabs.quaklog.entity.vo.*;
import br.com.luizalabs.quaklog.entrypoint.dto.*;
import br.com.luizalabs.quaklog.usecase.GameGetterUseCase;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameGetterRestEntryPointTest {

    private static final String NAME_PLAYER_1 = "JESUS";
    private static final String NAME_PLAYER_2 = "JOSE";
    private static final String NAME_PLAYER_3 = "MARIA";
    private GameGetterUseCase useCase = mock(GameGetterUseCase.class);
    private GameGetterRestEntryPoint gameGetter = new GameGetterRestEntryPoint(useCase);

    @Test
    void shouldReturnHttpStatusNotFoundWhenNoGameBeFound() {
        UUID uuid = UUID.randomUUID();
        when(useCase.getGameByUUID(GameUUID.of(uuid.toString()))).thenReturn(null);

        final ResponseEntity<GameDTO> response = gameGetter.getGameByUUIDEndpoint(uuid.toString());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnEmptyListWhenNoGamesBeFoundInSearchGameByDate() {
        final LocalDate now = LocalDate.now();
        when(useCase.getGamesByDate(now)).thenReturn(null);
        final SimpleListGamesDTO simpleListGamesDTO = gameGetter.searchGameByDate(now);
        assertNotNull(simpleListGamesDTO);
        assertEquals(0, simpleListGamesDTO.getQuantity().intValue());
        assertNotNull(simpleListGamesDTO.getGames());
        assertEquals(0, simpleListGamesDTO.getGames().size());
    }

    @Test
    void shouldReturnListOfGames() {
        final LocalDate now = LocalDate.now();
        List<Game> mocks = getTwoFakeGames();
        when(useCase.getGamesByDate(now)).thenReturn(mocks);
        final SimpleListGamesDTO simpleListGamesDTO = gameGetter.searchGameByDate(now);
        assertNotNull(simpleListGamesDTO);
        assertEquals(2, simpleListGamesDTO.getQuantity().intValue());
        assertNotNull(simpleListGamesDTO.getGames());
        assertEquals(2, simpleListGamesDTO.getGames().size());

        assertSimpleGames(simpleListGamesDTO, mocks);
    }


    @Test
    void shouldReturnDetailsGame() {
        UUID uuid = UUID.randomUUID();
        final Game mock = getFakeGame();
        when(useCase.getGameByUUID(GameUUID.of(uuid.toString()))).thenReturn(mock);

        final ResponseEntity<GameDTO> response = gameGetter.getGameByUUIDEndpoint(uuid.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final GameDTO body = response.getBody();
        assertGame(mock, body);

        List<Player> mockPlayers = mock.getPlayers();
        List<PlayerDTO> bodyPlayers = body.getPlayers();
        assertPlayers(mockPlayers, bodyPlayers);

        List<KillHistory> mockKdHistory = mock.getWorld().getKdHistory();
        List<KillHistoryDTO> bodyKdHistory = body.getWord().getKdHistory();
        assertKdHistory(mockKdHistory, bodyKdHistory);
    }

    private void assertSimpleGames(SimpleListGamesDTO simpleListGamesDTO, List<Game> mocks) {
        simpleListGamesDTO.getGames().values().forEach(dto -> {
            assertSimpleGame(dto, mocks.stream().filter(mock -> mock.getGameUUID().toString().equals(dto.getUuid())).findFirst());
        });
    }

    private void assertSimpleGame(SimpleGameDTO dto, Optional<Game> first) {
        if (!first.isPresent()) {
            fail();
            return;
        }
        final Game game = first.get();
        assertEquals(String.format("/%s/%s", RestConstants.PATH_GAME, game.getGameUUID().toString()), dto.getDetailPath());
        assertEquals(game.getTotalKills(), dto.getTotalKills());
        assertEquals(game.getGameUUID().toString(), dto.getUuid());
        assertSimplePlayers(dto.getPlayers(), game.getPlayers());
    }

    private void assertSimplePlayers(List<SimplePlayerDTO> dtos, List<Player> players) {
        IntStream.range(0, dtos.size()).forEach(i -> assertSimplePlayer(dtos.get(i), players.get(i)));
    }

    private void assertSimplePlayer(SimplePlayerDTO dto, Player player) {
        assertEquals(player.getId(), dto.getId());
        assertEquals(player.getKills(), dto.getKills());
        assertEquals(player.getName(), dto.getName());
    }


    private List<Game> getTwoFakeGames() {
        return Lists.newArrayList(getFakeGame(), getFakeGame());
    }

    private void assertGame(Game mock, GameDTO body) {
        assertNotNull(body);
        assertEquals(mock.getEndGameTime().toString(), body.getEndTime());
        assertEquals(mock.getStartGameTime().toString(), body.getStartTime());
        assertEquals(mock.getTotalKills(), body.getTotalKills());
        assertEquals(mock.getGameUUID().toString(), body.getUuid());
        assertEquals(mock.getWorld().getName(), body.getWord().getName());
        assertEquals(mock.getWorld().getId(), body.getWord().getId());
        assertEquals(mock.getWorld().getKills(), body.getWord().getKills());
        assertEquals(mock.getPlayers().size(), body.getPlayers().size());
    }

    private void assertPlayers(List<Player> mockPlayers, List<PlayerDTO> bodyPlayers) {
        for (int playerCount = 0; playerCount < mockPlayers.size(); playerCount++) {
            Player mockPlayer = mockPlayers.get(playerCount);
            PlayerDTO bodyPlayer = bodyPlayers.get(playerCount);
            assertEquals(mockPlayer.getId(), bodyPlayer.getId());
            assertEquals(mockPlayer.getKills(), bodyPlayer.getKills());
            assertEquals(mockPlayer.getName(), bodyPlayer.getName());
            assertEquals(mockPlayer.getStatus().size(), bodyPlayer.getStatus().size());
            List<Item> mockItems = mockPlayer.getItems();
            List<String> bodyItems = bodyPlayer.getItems();
            for (int itemCount = 0; itemCount < mockItems.size(); itemCount++) {
                assertEquals(mockItems.get(itemCount).toString(), bodyItems.get(itemCount));
            }
            List<KillHistory> mockKdHistory = mockPlayer.getKdHistory();
            List<KillHistoryDTO> bodyKdHistory = bodyPlayer.getKdHistory();
            assertKdHistory(mockKdHistory, bodyKdHistory);
        }
    }

    private void assertKdHistory(List<KillHistory> mockKdHistory, List<KillHistoryDTO> bodyKdHistory) {
        for (int kdCount = 0; kdCount < mockKdHistory.size(); kdCount++) {
            assertEquals(mockKdHistory.get(kdCount).getGameTime().toString(), bodyKdHistory.get(kdCount).getTime());
            assertEquals(mockKdHistory.get(kdCount).getKillMode().name(), bodyKdHistory.get(kdCount).getKillMode());
            assertEquals(mockKdHistory.get(kdCount).getMod().name(), bodyKdHistory.get(kdCount).getMod());
            assertEquals(mockKdHistory.get(kdCount).getPlayer(), bodyKdHistory.get(kdCount).getPlayer());
        }
    }

    private Game getFakeGame() {
        return new Game.GameBuilder(GameTime.of("01:01"), LocalDate.now())
                .setTotalKills(10)
                .setWorld(World.builder().kills(10).kdHistory(getWorldFakeKDHistory()).build())
                .setEndGameTime(GameTime.of("02:02"))
                .setPlayers(getFakePlayer())
                .build();
    }

    private Map<Integer, PlayerInGame> getFakePlayer() {
        Map<Integer, PlayerInGame> players = new HashMap<>();
        players.put(1, PlayerInGame.builder().id(1).kills(0).name(NAME_PLAYER_1).items(getFakeItems()).status(getBeginStatus()).kdHistory(getFakeKDHistory()).build());
        players.put(2, PlayerInGame.builder().id(2).kills(0).name(NAME_PLAYER_2).items(getFakeItems()).status(getBeginStatus()).kdHistory(getFakeKDHistory()).build());
        players.put(3, PlayerInGame.builder().id(3).kills(0).name(NAME_PLAYER_3).items(getFakeItems()).status(getBeginStatus()).kdHistory(getFakeKDHistory()).build());
        return players;
    }

    private List<KillHistory> getFakeKDHistory() {
        return Lists.newArrayList(
                KillHistory.builder().killMode(KillMode.KILL).mod(Mod.MOD_BFG).player(1).gameTime(GameTime.of("01:04")).build(),
                KillHistory.builder().killMode(KillMode.DEAD).mod(Mod.MOD_BFG_SPLASH).player(2).gameTime(GameTime.of("01:04")).build(),
                KillHistory.builder().killMode(KillMode.KILL).mod(Mod.MOD_RAILGUN).player(3).gameTime(GameTime.of("01:04")).build()
        );
    }


    private List<KillHistory> getWorldFakeKDHistory() {
        return Lists.newArrayList(
                KillHistory.builder().killMode(KillMode.KILL).mod(Mod.MOD_FALLING).player(1).gameTime(GameTime.of("01:05")).build(),
                KillHistory.builder().killMode(KillMode.KILL).mod(Mod.MOD_FALLING).player(2).gameTime(GameTime.of("01:05")).build(),
                KillHistory.builder().killMode(KillMode.KILL).mod(Mod.MOD_ROCKET_SPLASH).player(3).gameTime(GameTime.of("01:05")).build()
        );
    }

    private List<Item> getFakeItems() {
        return Lists.newArrayList(
                Item.valueOf("a"),
                Item.valueOf("b"),
                Item.valueOf("c")
        );
    }

    private List<PlayerStatus> getBeginStatus() {
        return Lists.newArrayList(
                PlayerStatus.builder().time(GameTime.of("01:03")).status(ConnectStatus.CONNECTED).build(),
                PlayerStatus.builder().time(GameTime.of("01:03")).status(ConnectStatus.BEGIN).build()
        );
    }

}