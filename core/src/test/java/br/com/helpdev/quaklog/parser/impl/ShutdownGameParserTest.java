package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.ShutdownGameParser;
import br.com.helpdev.quaklog.processor.parser.objects.ShutdownGameObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShutdownGameParserTest {

    private final ShutdownGameParser parser = new ShutdownGameParser();

    private enum ShutdownGame {
        FIRST("  0:25 ShutdownGame: ", "0:25"),
        SECOND(" 10:25 ShutdownGame: ", "10:25"),
        THIRD("100:25 ShutdownGame: ", "100:25"),
        FOURTH("  5:25 ShutdownGame: ", "5:25");

        String line;
        String expectedTime;

        ShutdownGame(String line, String expectedTime) {
            this.line = line;
            this.expectedTime = expectedTime;
        }
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(ShutdownGame.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(ShutdownGame valueToParse) {
        ShutdownGameObParser parse = parse(valueToParse.line);
        return parse.getGameTime().equals(valueToParse.expectedTime);
    }

    private ShutdownGameObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }

}