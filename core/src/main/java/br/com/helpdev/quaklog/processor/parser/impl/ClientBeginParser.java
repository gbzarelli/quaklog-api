package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.objects.ClientBeginParseObParser;

public class ClientBeginParser implements SingleIDParser<ClientBeginParseObParser> {

    @Override
    public ClientBeginParseObParser parse(final String value) throws GameParserException {
        try {
            return ClientBeginParseObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractSingleIDAfterKeyPattern(value))
                    .build();
        } catch (Exception t) {
            throw new GameParserException(t.getMessage(), t);
        }
    }

}
