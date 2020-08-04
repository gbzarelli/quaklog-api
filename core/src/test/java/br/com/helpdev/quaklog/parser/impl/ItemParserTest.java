package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.impl.ItemParser;
import br.com.helpdev.quaklog.processor.parser.objects.ItemObParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemParserTest {

    private final ItemParser parser = new ItemParser();

    private enum Item {
        FIRST("1:14 Item: 5 weapon_rocketlauncher", "1:14", 5, "weapon_rocketlauncher"),
        SECOND("  1:14 Item: 6 weapon_railgun", "1:14", 6, "weapon_railgun"),
        THIRD("1:15 Item: 3 weapon_rocketlauncher", "1:15", 3, "weapon_rocketlauncher"),
        FOURTH("  1:17 Item: 6 weapon_railgun", "1:17", 6, "weapon_railgun");

        String line;
        String expectedTime;
        Integer expectedID;
        String expectedItem;

        Item(String line, String expectedTime, Integer expectedID, String expectedItem) {
            this.line = line;
            this.expectedTime = expectedTime;
            this.expectedID = expectedID;
            this.expectedItem = expectedItem;
        }
    }

    @Test
    void assertThrowsWhenParseInvalidItemExtract() {
        assertThrows(GameParserException.class, () -> parser.parse("  1:17 Item: 6"));
    }

    @Test
    void shouldThrowGameParseExceptionWhenParseInvalidFormat() {
        assertThrows(GameParserException.class, () -> parser.parse("abc abc abc"));
    }

    @Test
    void parseClientBeginWithSuccess() {
        assertTrue(Arrays.stream(Item.values()).allMatch(this::assertParse));
    }

    private boolean assertParse(Item valueToParse) {
        ItemObParser parse = parse(valueToParse.line);
        return parse.getId().equals(valueToParse.expectedID) && parse.getGameTime().equals(valueToParse.expectedTime) &&
                parse.getItem().equals(valueToParse.expectedItem);
    }

    private ItemObParser parse(String value) {
        try {
            return parser.parse(value);
        } catch (GameParserException e) {
            throw new RuntimeException(e);
        }
    }
}