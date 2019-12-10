package br.com.luizalabs.quaklog.processor.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameParserKey;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.InitGameObParser;
import br.com.luizalabs.quaklog.processor.GameParseProcessor;
import lombok.val;

import javax.inject.Named;

@Named
class GameParseProcessorImpl implements GameParseProcessor {

    @Override
    public Game.GameBuilder initGame(Parsable<InitGameObParser> gameParser, String line) throws GameParserException {
        val initGameObParser = gameParser.parse(line);
        val gameTime = new GameTime(initGameObParser.getGameTime());
        return new Game.GameBuilder(gameTime).gameParameters(initGameObParser.getArguments());
    }

    @Override
    public void processLine(Game.GameBuilder gameBuilder, GameParserKey parserKey, String line) throws GameParserException {
        //TODO
        switch (parserKey) {
            case CLIENT_CONNECT:
            case CLIENT_BEGIN:
            case KILL:
            case SHUTDOWN_GAME:
            case ITEM:
            case CLIENT_DISCONNECT:
            case CLIENT_USER_INFO_CHANGED:
            default:
                gameBuilder.addNotification("KEY NOT FOUND IN PARSER BUILDER PROCESSOR {" + parserKey + "}");
        }
    }
}
