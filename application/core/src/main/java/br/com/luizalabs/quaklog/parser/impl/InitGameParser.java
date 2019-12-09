package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameRegexUtils;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.InitGameObParser;
import lombok.val;

import java.util.Collections;
import java.util.Map;

public class InitGameParser implements Parsable<InitGameObParser> {

    @Override
    public InitGameObParser parse(String value) throws GameParserException {
        try {
            final val stringStringMap = extractParameters(value);
            return InitGameObParser.builder()
                    .gameTime(extractTime(value))
                    .arguments(stringStringMap)
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private Map<String, String> extractParameters(String value) {
        //ISSUE: Poderia pensar em um regex melhor
        final val matcher = GameRegexUtils.AFTER_KEY.matcher(value);
        if (matcher.find()) {
            final val afterKey = matcher.group();
            return GameRegexUtils.extractPairsMap("\\", afterKey);
        }
        return Collections.emptyMap();
    }

}
