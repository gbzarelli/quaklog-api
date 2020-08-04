package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.parser.objects.ShutdownGameObParser;

public class ShutdownGameParser implements Parsable<ShutdownGameObParser> {

    @Override
    public ShutdownGameObParser parse(final String value) throws GameParserException {
        try {
            return ShutdownGameObParser.builder().gameTime(extractTime(value)).build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }
}
