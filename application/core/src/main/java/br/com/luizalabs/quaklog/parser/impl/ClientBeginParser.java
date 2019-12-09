package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.objects.ClientBeginParseObParser;

public class ClientBeginParser implements SingleIDParser<ClientBeginParseObParser> {

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

}
