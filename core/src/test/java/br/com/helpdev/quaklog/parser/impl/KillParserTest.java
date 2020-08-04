package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.KillParser;
import br.com.helpdev.quaklog.processor.parser.objects.KillObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KillParserTest {

    private final KillParser parser = new KillParser();

    private enum Kill {
        FIRST("14:52 Kill: 5 3 3: Oootsimo killed Dono da Bola by MOD_MACHINEGUN", "14:52", 5, 3, 3, "Oootsimo", "Dono da Bola", "MOD_MACHINEGUN"),
        SECOND("14:54 Kill: 1022 7 22: <world> killed Assasinu Credi by MOD_TRIGGER_HURT", "14:54", 1022, 7, 22, "<world>", "Assasinu Credi", "MOD_TRIGGER_HURT"),
        THIRD("14:57 Kill: 2 6 13: Isgalamido killed Chessus by MOD_BFG_SPLASH", "14:57", 2, 6, 13, "Isgalamido", "Chessus", "MOD_BFG_SPLASH"),
        FOURTH("15:13 Kill: 7 4 7: Assasinu Credi killed Zeh by MOD_ROCKET_SPLASH", "15:13", 7, 4, 7, "Assasinu Credi", "Zeh", "MOD_ROCKET_SPLASH");

        String line;
        KillObParser killObParser;

        Kill(String line, String expectedTime,
             Integer killerID,
             Integer killedID,
             Integer modeID,
             String killerName,
             String killedName,
             String mode
        ) {
            this.line = line;
            killObParser = KillObParser.builder()
                    .gameTime(expectedTime)
                    .killerID(killerID)
                    .killedID(killedID)
                    .killedModeID(modeID)
                    .killer(killerName)
                    .killed(killedName)
                    .killedMode(mode)
                    .build();
        }
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(Kill.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(Kill valueToParse) {
        KillObParser parse = parse(valueToParse.line);
        return parse.equals(valueToParse.killObParser);
    }

    private KillObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }
}