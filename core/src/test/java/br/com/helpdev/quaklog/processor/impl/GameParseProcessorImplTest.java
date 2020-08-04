package br.com.helpdev.quaklog.processor.impl;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.Player;
import br.com.helpdev.quaklog.entity.PlayerInGame;
import br.com.helpdev.quaklog.entity.vo.ConnectStatus;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.GameParserKey;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GameParseProcessorImplTest {

    private static final String INIT_GAME_LINE = "01:01 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
    private final GameParseProcessorImpl parser = new GameParseProcessorImpl();
    private final GameParserKey initGame = GameParserKey.INIT_GAME;
    private final LocalDate localDate = LocalDate.now();

    @Test
    void shouldProcessInitGameWithSuccess() throws GameParserException {
        final var gameBuilder = initGame(INIT_GAME_LINE);
        final var build = gameBuilder.build();
        assertEquals(GameTime.of("01:01"), build.getStartGameTime());
        assertEquals(20, build.getGameParameters().size());
        assertEquals(0, build.getPlayers().size());
        assertEquals(0, build.getTotalKills().intValue());
        assertNotNull(build.getWorld());
        assertNull(build.getEndGameTime());
    }

    @Test
    void shouldProcessShutdownGameWithSuccess() throws GameParserException {
        final var gameBuilder = initGame(INIT_GAME_LINE);
        parser.processLine(gameBuilder, GameParserKey.SHUTDOWN_GAME, "30:10 ShutdownGame: ");
        final var build = gameBuilder.build();

        assertEquals(GameTime.of("01:01"), build.getStartGameTime());
        assertEquals(20, build.getGameParameters().size());
        assertEquals(0, build.getPlayers().size());
        assertEquals(0, build.getTotalKills().intValue());
        assertNotNull(build.getWorld());
        assertNotNull(build.getEndGameTime());
        assertEquals("30:10", build.getEndGameTime().toString());
    }

    @Test
    void shouldThrowGameParserExceptionWhenParseInitGameWithInvalidInput() {
        assertThrows(GameParserException.class, () -> initGame("ABC"));
    }

    @Test
    void shouldProcessLineClientConnectWithSuccess() throws GameParserException {
        final var gameBuilder = connectClient(initGame(INIT_GAME_LINE), " 20:34 ClientConnect: 2");
        assertNotNull(gameBuilder.getPlayerKiller(2));
        assertNotNull(gameBuilder.getPlayerInGame(2));
        assertEquals(gameBuilder.getPlayerKiller(2), gameBuilder.getPlayerInGame(2));
        assertEquals(ConnectStatus.CONNECTED, gameBuilder.getPlayerInGame(2).getConnectStatus());
    }

    @Test
    void shouldProcessLineClientInfoChangedWithSuccess() throws GameParserException {
        final var gameBuilder = connectClient(initGame(INIT_GAME_LINE), " 20:34 ClientConnect: 4");
        addInfos(gameBuilder, "20:34 ClientUserinfoChanged: 4 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");

        final var user = gameBuilder.getPlayerKiller(4);
        assertNotNull(user);
        assertNotNull(gameBuilder.getPlayerInGame(4));
        assertEquals(user, gameBuilder.getPlayerInGame(4));
        assertEquals(ConnectStatus.CONNECTED, gameBuilder.getPlayerInGame(4).getConnectStatus());
        assertEquals("Isgalamido", user.getName());
    }

    @Test
    void shouldProcessLineClientBeginWithSuccess() throws GameParserException {
        final var gameBuilder = connectClient(initGame(INIT_GAME_LINE), " 20:34 ClientConnect: 4");
        addInfos(gameBuilder, "20:34 ClientUserinfoChanged: 4 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
        beginClient(gameBuilder, "20:37 ClientBegin: 4");

        final var user = gameBuilder.getPlayerKiller(4);
        assertNotNull(user);
        assertNotNull(gameBuilder.getPlayerInGame(4));
        assertEquals(user, gameBuilder.getPlayerInGame(4));
        assertEquals(ConnectStatus.BEGIN, gameBuilder.getPlayerInGame(4).getConnectStatus());
        assertEquals("Isgalamido", user.getName());
    }

    @Test
    void shouldProcessLineDisconnectWithSuccess() throws GameParserException {
        final Game.GameBuilder builder = getGameWithOneUserWithSuccess();
        connectClient(builder, " 20:38 ClientConnect: 2");
        addInfos(builder, "20:38 ClientUserinfoChanged: 2 n\\XXXX\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
        beginClient(builder, " 20:38 ClientBegin: 2");
        parser.processLine(builder, GameParserKey.CLIENT_DISCONNECT, "30:00 ClientDisconnect: 4");
        assertEquals("Isgalamido", builder.getPlayerInGame(4).getName());
        assertEquals("XXXX", builder.getPlayerInGame(2).getName());
        assertEquals(ConnectStatus.DISCONNECTED, builder.getPlayerInGame(4).getConnectStatus());
        assertEquals(ConnectStatus.BEGIN, builder.getPlayerInGame(2).getConnectStatus());
    }

    @Test
    void shouldProcessKillsWithSuccess() throws GameParserException {
        final Game.GameBuilder builder = getGameWithOneUserWithSuccess();

        //*connect rival
        connectClient(builder, " 20:38 ClientConnect: 2");
        addInfos(builder, "20:38 ClientUserinfoChanged: 2 n\\XXXX\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
        beginClient(builder, " 20:38 ClientBegin: 2");

        //*assert user begins
        assertEquals("Isgalamido", builder.getPlayerInGame(4).getName());
        assertEquals("XXXX", builder.getPlayerInGame(2).getName());
        assertEquals(ConnectStatus.BEGIN, builder.getPlayerInGame(4).getConnectStatus());
        assertEquals(ConnectStatus.BEGIN, builder.getPlayerInGame(2).getConnectStatus());

        parser.processLine(builder, GameParserKey.KILL, "20:54 Kill: 1022 2 22: <world> killed XXXX by MOD_TRIGGER_HURT");
        parser.processLine(builder, GameParserKey.KILL, "20:54 Kill: 1022 4 22: <world> killed Isgalamido by MOD_TRIGGER_HURT");
        parser.processLine(builder, GameParserKey.KILL, "20:54 Kill: 1022 4 22: <world> killed Isgalamido by MOD_TRIGGER_HURT");
        parser.processLine(builder, GameParserKey.KILL, "20:54 Kill: 4 2 7: Isgalamido killed XXXX by MOD_ROCKET_SPLASH");
        parser.processLine(builder, GameParserKey.KILL, "21:54 Kill: 1022 2 22: <world> killed XXXX by MOD_TRIGGER_HURT");

        final Game game = builder.build();

        final Player isgalamido = game.getPlayerByID(4);
        final Player xxxx = game.getPlayerByID(2);

        assertEquals(5, game.getTotalKills().intValue());
        assertEquals(-1, isgalamido.getKills().intValue());
        assertEquals(-2, xxxx.getKills().intValue());
        assertEquals(4, game.getWorld().getKdHistory().size());
        assertEquals(3, xxxx.getKdHistory().size());
        assertEquals(3, isgalamido.getKdHistory().size());
    }


    @Test
    void shouldAddItemsWithSuccess() throws GameParserException {
        final Game.GameBuilder builder = getGameWithOneUserWithSuccess();
        parser.processLine(builder, GameParserKey.ITEM, "20:40 Item: 4 weapon_rocketlauncher");
        assertTrueAddItemWithInexistingPlayer(builder);
        parser.processLine(builder, GameParserKey.ITEM, " 20:50 Item: 4 item_armor_body");
        final PlayerInGame player = builder.getPlayerInGame(4);
        assertEquals(2, player.getItems().size());
        assertEquals("weapon_rocketlauncher", player.getItems().get(0).toString());
        assertEquals("item_armor_body", player.getItems().get(1).toString());
    }

    private void beginClient(Game.GameBuilder gameBuilder, String line) throws GameParserException {
        parser.processLine(gameBuilder, GameParserKey.CLIENT_BEGIN, line);
    }

    private void assertTrueAddItemWithInexistingPlayer(Game.GameBuilder builder) {
        assertThrows(NullPointerException.class, () -> {
            parser.processLine(builder, GameParserKey.ITEM, " 20:42 Item: 2 item_armor_body");
        });
    }

    private Game.GameBuilder getGameWithOneUserWithSuccess() throws GameParserException {
        final Game.GameBuilder gameBuilder = initGame(INIT_GAME_LINE);
        connectClient(gameBuilder, "20:34 ClientConnect: 4");
        addInfos(gameBuilder, "20:34 ClientUserinfoChanged: 4 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
        beginClient(gameBuilder, "20:37 ClientBegin: 4");
        return gameBuilder;
    }

    private Game.GameBuilder initGame(String initGameLine) throws GameParserException {
        return parser.initGame(localDate, initGame.getParsable(), initGameLine);
    }

    private Game.GameBuilder addInfos(Game.GameBuilder gameBuilder, String line) throws GameParserException {
        parser.processLine(gameBuilder, GameParserKey.CLIENT_USER_INFO_CHANGED, line);
        return gameBuilder;
    }

    private Game.GameBuilder connectClient(Game.GameBuilder gameBuilder, String line) throws GameParserException {
        parser.processLine(gameBuilder, GameParserKey.CLIENT_CONNECT, line);
        return gameBuilder;
    }

}