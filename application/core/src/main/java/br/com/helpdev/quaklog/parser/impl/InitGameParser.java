package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameParserException;
import br.com.helpdev.quaklog.parser.GameRegexUtils;
import br.com.helpdev.quaklog.parser.Parsable;
import br.com.helpdev.quaklog.parser.objects.InitGameObParser;
import lombok.val;

import java.util.Collections;
import java.util.Map;

public class InitGameParser implements Parsable<InitGameObParser> {

    @Override
    public InitGameObParser parse(String value) throws GameParserException {
        try {
            val stringStringMap = extractParameters(value);
            return InitGameObParser.builder()
                    .gameTime(extractTime(value))
                    .arguments(stringStringMap)
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private Map<String, String> extractParameters(String value) {
        val matcher = GameRegexUtils.AFTER_KEY.matcher(value);
        if (matcher.find()) {
            val afterKey = matcher.group();
            return GameRegexUtils.extractPairsMap("\\", afterKey);
        }
        return Collections.emptyMap();
    }

}
