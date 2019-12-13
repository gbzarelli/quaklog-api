package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameParserKey;
import br.com.luizalabs.quaklog.processor.GameParseProcessor;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameImporterUseCaseImplTest {

    private final GameParseProcessor parser = mock(GameParseProcessor.class);
    private final GameRepository repository = mock(GameRepository.class);
    private final InputStream is = mock(InputStream.class);

    private final GameImporterUseCaseImpl useCase = new GameImporterUseCaseImpl(parser, repository);

    @Test
    void shouldNotImportGamesWithEmptyStream() throws IOException {
        when(is.available()).thenReturn(0);
        GamesImported gamesImported = useCase.importGame(LocalDate.now(), is);
        assertNotNull(gamesImported);
        assertEquals(0, gamesImported.getGames().size());
    }

    @Test
    void shouldThrowIOExceptionWhenHaveFailedToReadStream() throws IOException {
        when(is.available()).thenReturn(10);
        when(is.read()).thenThrow(new IOException());

        assertThrows(IOException.class, () -> {
            useCase.importGame(LocalDate.now(), is);
        });
    }

    @Test
    void shouldImportGameWithSuccess() throws IOException, GameParserException {
        LocalDate importDate = LocalDate.now();
        Game.GameBuilder gameBuilder = mock(Game.GameBuilder.class);
        Game game = mock(Game.class);

        String a = "  0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
        when(parser.initGame(importDate, GameParserKey.INIT_GAME.getParsable(), a)).thenReturn(gameBuilder);
        when(gameBuilder.build()).thenReturn(game);

        final InputStream inputStream = Files.newInputStream(Paths.get("test_files/game_1.log"));
        final GamesImported gamesImported = useCase.importGame(importDate, inputStream);
        assertEquals(1, gamesImported.getGames().size());
        assertEquals(game.getGameUUID(), gamesImported.getGames().get(0));
    }

    @Test
    void shouldImportGamesWhenTheFirstGameNotHaveShutdownKey() throws IOException, GameParserException {
        LocalDate importDate = LocalDate.now();
        Game.GameBuilder gameBuilder1 = mock(Game.GameBuilder.class);
        Game game1 = mock(Game.class);
        Game.GameBuilder gameBuilder2 = mock(Game.GameBuilder.class);
        Game game2 = mock(Game.class);

        String a = "0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
        when(parser.initGame(importDate, GameParserKey.INIT_GAME.getParsable(), a)).thenReturn(gameBuilder1);
        when(gameBuilder1.build()).thenReturn(game1);

        String b = "0:00 InitGame: \\sv_floodProtect\\2\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
        when(parser.initGame(importDate, GameParserKey.INIT_GAME.getParsable(), b)).thenReturn(gameBuilder2);
        when(gameBuilder2.build()).thenReturn(game2);

        final InputStream inputStream = Files.newInputStream(Paths.get("test_files/game_2.log"));
        final GamesImported gamesImported = useCase.importGame(importDate, inputStream);
        assertEquals(2, gamesImported.getGames().size());
        assertEquals(game1.getGameUUID(), gamesImported.getGames().get(0));
        assertEquals(game2.getGameUUID(), gamesImported.getGames().get(1));
    }


    @Test
    void shouldImportOneGameWithFileStartWithAGameWithoutInitGameKey() throws IOException, GameParserException {
        LocalDate importDate = LocalDate.now();
        Game.GameBuilder gameBuilder = mock(Game.GameBuilder.class);
        Game game = mock(Game.class);

        String a = "0:00 InitGame: \\sv_floodProtect\\2\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
        when(parser.initGame(importDate, GameParserKey.INIT_GAME.getParsable(), a)).thenReturn(gameBuilder);
        when(gameBuilder.build()).thenReturn(game);

        final InputStream inputStream = Files.newInputStream(Paths.get("test_files/game_3.log"));
        final GamesImported gamesImported = useCase.importGame(importDate, inputStream);
        assertEquals(1, gamesImported.getGames().size());
        assertEquals(game.getGameUUID(), gamesImported.getGames().get(0));
        assertEquals("1 - No games are running and receive: {ClientConnect} with value: { 20:34 ClientConnect: 2}", gamesImported.getNotifications().get(0));
    }

}