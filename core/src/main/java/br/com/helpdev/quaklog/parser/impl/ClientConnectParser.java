package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameParserException;
import br.com.helpdev.quaklog.parser.objects.ClientConnectObParser;

public class ClientConnectParser implements SingleIDParser<ClientConnectObParser> {

    @Override
    public ClientConnectObParser parse(String value) throws GameParserException {
        try {
            return ClientConnectObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractSingleIDAfterKeyPattern(value))
                    .build();
        } catch (Exception t) {
            throw new GameParserException(t.getMessage(), t);
        }
    }
}
