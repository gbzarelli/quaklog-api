package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.ClientUserInfoChangedParser;
import br.com.helpdev.quaklog.processor.parser.objects.ClientUserInfoChangedObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientUserInfoChangedParserTest {

    private final ClientUserInfoChangedParser parser = new ClientUserInfoChangedParser();

    private enum ClientUserInfoChanged {
        FIRST(" 21:51 ClientUserinfoChanged: 3 n\\Dono da Bola\\t\\0\\model\\sarge/krusade\\hmodel\\sarge/krusade\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0",
                "21:51", 3, "Dono da Bola"),
        SECOND(" 21:53 ClientUserinfoChanged: 4 n\\Mocinha\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0",
                "21:53", 4, "Mocinha");

        String line;
        String expectedTime;
        Integer expectedID;
        String name;

        ClientUserInfoChanged(String line, String expectedTime, Integer expectedID, String name) {
            this.line = line;
            this.expectedTime = expectedTime;
            this.expectedID = expectedID;
            this.name = name;
        }
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(ClientUserInfoChanged.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(ClientUserInfoChanged valueToParse) {
        ClientUserInfoChangedObParser parse = parse(valueToParse.line);
        return parse.getId() == valueToParse.expectedID && parse.getGameTime().equals(valueToParse.expectedTime) &&
                parse.getName().equals(valueToParse.name);
    }

    private ClientUserInfoChangedObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }
}