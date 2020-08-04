package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.ClientDisconnectParser;
import br.com.helpdev.quaklog.processor.parser.objects.ClientDisconnectObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientDisconnectParserTest {

    private final ClientDisconnectParser parser = new ClientDisconnectParser();

    private enum ClientsDisconnect {
        FIRST("  0:25 ClientDisconnect: 2", "0:25", 2),
        SECOND(" 10:25 ClientDisconnect: 1", "10:25", 1),
        THIRD("100:25 ClientDisconnect: 3", "100:25", 3),
        FOURTH("  5:25 ClientDisconnect: 5", "5:25", 5);

        String line;
        String expectedTime;
        Integer expectedID;

        ClientsDisconnect(String line, String expectedTime, Integer expectedID) {
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
        assertTrue(Arrays.stream(ClientsDisconnect.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(ClientsDisconnect valueToParse) {
        ClientDisconnectObParser parse = parse(valueToParse.line);
        return parse.getId() == valueToParse.expectedID && parse.getGameTime().equals(valueToParse.expectedTime);
    }

    private ClientDisconnectObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }
}