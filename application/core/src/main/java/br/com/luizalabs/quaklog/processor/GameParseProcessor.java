package br.com.luizalabs.quaklog.processor;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.ParseObject;
import br.com.luizalabs.quaklog.parser.objects.InitGameObParser;

public interface GameParseProcessor {

    Game.GameBuilder initGame(Parsable<InitGameObParser> gameParser, String line) throws GameParserException;

    void processLine(Game.GameBuilder gameBuilder, Parsable<ParseObject> gameParser, String line) throws GameParserException;

}
