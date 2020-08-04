package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.ClientBeginParser;
import br.com.helpdev.quaklog.processor.parser.objects.ClientBeginParseObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientBeginParserTest {

    private final ClientBeginParser parser = new ClientBeginParser();

    private enum ClientsBegin {
        FIRST("  0:25 ClientBegin: 2", "0:25", 2),
        SECOND(" 10:25 ClientBegin: 1", "10:25", 1),
        THIRD("100:25 ClientBegin: 3", "100:25", 3),
        FOURTH("  5:25 ClientBegin: 5", "5:25", 5);

        String line;
        String expectedTime;
        Integer expectedID;

        ClientsBegin(String line, String expectedTime, Integer expectedID) {
            this.line = line;
            this.expectedTime = expectedTime;
            this.expectedID = expectedID;
        }
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(ClientsBegin.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(ClientsBegin valueToParse) {
        ClientBeginParseObParser parse = parse(valueToParse.line);
        return parse.getId().equals(valueToParse.expectedID) && parse.getGameTime().equals(valueToParse.expectedTime);
    }

    private ClientBeginParseObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }

}