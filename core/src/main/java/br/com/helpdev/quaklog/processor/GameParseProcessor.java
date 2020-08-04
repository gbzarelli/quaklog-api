package br.com.helpdev.quaklog.processor;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.GameParserKey;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.parser.objects.InitGameObParser;

import java.time.LocalDate;

public interface GameParseProcessor {

    Game.GameBuilder initGame(LocalDate gameDate,
                              Parsable<InitGameObParser> gameParser,
                              String line) throws GameParserException;

    void processLine(Game.GameBuilder gameBuilder,
                     GameParserKey parserKey,
                     String line) throws GameParserException;

}
