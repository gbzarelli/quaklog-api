package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameParserException;
import br.com.helpdev.quaklog.parser.Parsable;
import br.com.helpdev.quaklog.parser.objects.ShutdownGameObParser;

public class ShutdownGameParser implements Parsable<ShutdownGameObParser> {

    @Override
    public ShutdownGameObParser parse(String value) throws GameParserException {
        try {
            return ShutdownGameObParser.builder().gameTime(extractTime(value)).build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }
}
