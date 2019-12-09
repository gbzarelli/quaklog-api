package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.ClientBeginParseObParser;
import lombok.val;

import static br.com.luizalabs.quaklog.parser.GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN;

public class ClientBeginParser implements Parsable<ClientBeginParseObParser> {

    @Override
    public ClientBeginParseObParser parse(String value) throws GameParserException {
        try {
            return ClientBeginParseObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractSingleIDAfterKeyPattern(value))
                    .build();
        } catch (Exception t) {
            throw new GameParserException(t.getMessage(), t);
        }
    }

    private Integer extractSingleIDAfterKeyPattern(String value) {
        final val matcher = SINGLE_ID_AFTER_KEY_PATTERN.matcher(value);
        if (matcher.find()) return Integer.parseInt(matcher.group().trim());
        return -1;
    }
}
