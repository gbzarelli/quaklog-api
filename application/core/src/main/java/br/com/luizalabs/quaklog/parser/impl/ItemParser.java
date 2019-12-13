package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameRegexUtils;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.ItemObParser;
import lombok.val;

import java.util.Objects;

import static br.com.luizalabs.quaklog.parser.GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN;
import static br.com.luizalabs.quaklog.parser.GameRegexUtils.extractInteger;

public class ItemParser implements Parsable<ItemObParser> {

    @Override
    public ItemObParser parse(String value) throws GameParserException {
        try {
            return ItemObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractItemID(value))
                    .item(Objects.requireNonNull(extractItem(value)))
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private String extractItem(String value) {
        final val matcher = GameRegexUtils.AFTER_KEY_AND_NUMBER_GROUP2.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private Integer extractItemID(String value) {
        return extractInteger(SINGLE_ID_AFTER_KEY_PATTERN, value, -1);
    }

}
