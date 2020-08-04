package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.objects.ClientDisconnectObParser;

public class ClientDisconnectParser implements SingleIDParser<ClientDisconnectObParser> {

    @Override
    public ClientDisconnectObParser parse(final String value) throws GameParserException {
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
