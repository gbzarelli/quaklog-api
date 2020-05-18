package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameParserException;
import br.com.helpdev.quaklog.parser.objects.ClientDisconnectObParser;

public class ClientDisconnectParser implements SingleIDParser<ClientDisconnectObParser> {

    @Override
    public ClientDisconnectObParser parse(String value) throws GameParserException {
        try {
            return ClientDisconnectObParser.builder()
                    .gameTime(extractTime(value))
                    .id(extractSingleIDAfterKeyPattern(value))
                    .build();
        } catch (Exception t) {
            throw new GameParserException(t.getMessage(), t);
        }
    }
}
