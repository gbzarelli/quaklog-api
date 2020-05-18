package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameParserException;
import br.com.helpdev.quaklog.parser.GameRegexUtils;
import br.com.helpdev.quaklog.parser.Parsable;
import br.com.helpdev.quaklog.parser.objects.InitGameObParser;


import java.util.Collections;
import java.util.Map;

public class InitGameParser implements Parsable<InitGameObParser> {

    @Override
    public InitGameObParser parse(final String value) throws GameParserException {
        try {
            final var stringStringMap = extractParameters(value);
            return InitGameObParser.builder()
                    .gameTime(extractTime(value))
                    .arguments(stringStringMap)
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private Map<String, String> extractParameters(final String value) {
        final var matcher = GameRegexUtils.AFTER_KEY.matcher(value);
        if (matcher.find()) {
            final var afterKey = matcher.group();
            return GameRegexUtils.extractPairsMap("\\", afterKey);
        }
        return Collections.emptyMap();
    }

}
