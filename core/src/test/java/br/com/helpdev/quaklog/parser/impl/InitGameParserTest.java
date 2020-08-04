package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.InitGameParser;
import br.com.helpdev.quaklog.processor.parser.objects.InitGameObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InitGameParserTest {

    private final InitGameParser parser = new InitGameParser();

    private enum InitGame {
        FIRST(" 20:37 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\bot_minplayers\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0",
                "20:37", 21),
        SECOND("  2:33 InitGame: \\capturelimit\\8\\g_maxGameClients\\0\\timelimit\\15\\fraglimit\\20\\dmflags\\0\\bot_minplayers\\0\\sv_allowDownload\\0\\sv_maxclients\\16\\sv_privateClients\\2\\g_gametype\\4\\sv_hostname\\Code Miner Server\\sv_minRate\\0\\sv_maxRate\\10000\\sv_minPing\\0\\sv_maxPing\\0\\sv_floodProtect\\1\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\Q3TOURNEY6_CTF\\gamename\\baseq3\\g_needpass\\0",
                "2:33", 21);

        String line;
        String expectedTime;
        Integer parametersSize;

        InitGame(String line, String expectedTime, Integer parametersSize) {
            this.line = line;
            this.expectedTime = expectedTime;
            this.parametersSize = parametersSize;
        }
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(InitGame.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(InitGame valueToParse) {
        InitGameObParser parse = parse(valueToParse.line);
        return parse.getGameTime().equals(valueToParse.expectedTime) &&
                parse.getArguments().size() == valueToParse.parametersSize;
    }

    private InitGameObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }
}