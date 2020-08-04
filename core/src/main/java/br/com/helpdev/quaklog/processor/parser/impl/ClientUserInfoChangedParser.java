package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.GameRegexUtils;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.parser.objects.ClientUserInfoChangedObParser;


import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class ClientUserInfoChangedParser implements Parsable<ClientUserInfoChangedObParser> {

    @Override
    public ClientUserInfoChangedObParser parse(final String value) throws GameParserException {
        try {
            final var stringStringMap = extractParameters(value);
            return ClientUserInfoChangedObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractUserID(value))
                    .arguments(stringStringMap)
                    .name(Objects.requireNonNull(stringStringMap).get("n"))
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private Integer extractUserID(final String value) {
        return GameRegexUtils.extractInteger(GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN, value, -1);
    }

    private Map<String, String> extractParameters(final String value) {
        final var matcher = GameRegexUtils.AFTER_KEY_AND_NUMBER_GROUP2.matcher(value);
        if (matcher.find()) {
            final var parameters = matcher.group(1);
            return GameRegexUtils.extractPairsMap("\\", parameters);
        }
        return Collections.emptyMap();
    }


}
