package br.com.luizalabs.quaklog.processor.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.ParseObject;
import br.com.luizalabs.quaklog.parser.objects.InitGameObParser;
import br.com.luizalabs.quaklog.processor.GameParseProcessor;

import javax.inject.Named;

@Named
class GameParseProcessorImpl implements GameParseProcessor {

    @Override
    public Game.GameBuilder initGame(Parsable<InitGameObParser> gameParser, String line) throws GameParserException {
        //TODO
        return null;
    }

    @Override
    public void processLine(Game.GameBuilder gameBuilder, Parsable<ParseObject> gameParser, String line) throws GameParserException {
        //TODO
    }
}
