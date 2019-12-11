package br.com.luizalabs.quaklog.processor.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.Item;
import br.com.luizalabs.quaklog.entity.PlayerInGame;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.Mod;
import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameParserKey;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.*;
import br.com.luizalabs.quaklog.processor.GameParseProcessor;
import lombok.val;

import javax.inject.Named;
import java.time.LocalDate;

@Named
class GameParseProcessorImpl implements GameParseProcessor {

    @Override
    public Game.GameBuilder initGame(LocalDate gameDate, Parsable<InitGameObParser> gameParser, String line) throws GameParserException {
        val initGameObParser = gameParser.parse(line);
        val gameTime = GameTime.of(initGameObParser.getGameTime());
        return new Game.GameBuilder(gameTime, gameDate).setGameParameters(initGameObParser.getArguments());
    }

    @Override
    public void processLine(Game.GameBuilder gameBuilder, GameParserKey parserKey, String line) throws GameParserException {
        switch (parserKey) {
            case CLIENT_CONNECT:
                processClientConnected(gameBuilder, (ClientConnectObParser) parserKey.getParsable().parse(line));
                break;
            case CLIENT_USER_INFO_CHANGED:
                processClientUserInfoChanged(gameBuilder, (ClientUserInfoChangedObParser) parserKey.getParsable().parse(line));
                break;
            case CLIENT_BEGIN:
                processClientBegin(gameBuilder, (ClientBeginParseObParser) parserKey.getParsable().parse(line));
                break;
            case ITEM:
                processItem(gameBuilder, (ItemObParser) parserKey.getParsable().parse(line));
                break;
            case KILL:
                processKill(gameBuilder, (KillObParser) parserKey.getParsable().parse(line));
                break;
            case CLIENT_DISCONNECT:
                processClientDisconnected(gameBuilder, (ClientDisconnectObParser) parserKey.getParsable().parse(line));
                break;
            case SHUTDOWN_GAME:
                processShutdownGame(gameBuilder, (ShutdownGameObParser) parserKey.getParsable().parse(line));
                break;
            default:
                gameBuilder.addNotification("KEY NOT FOUND IN PARSER BUILDER PROCESSOR {" + parserKey + "}");
        }
    }

    private void processShutdownGame(Game.GameBuilder gameBuilder, ShutdownGameObParser parse) {
        gameBuilder.setEndGameTime(GameTime.of(parse.getGameTime()));
    }

    private void processClientDisconnected(Game.GameBuilder gameBuilder, ClientDisconnectObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).disconnect(GameTime.of(parse.getGameTime()));
    }

    private void processKill(Game.GameBuilder gameBuilder, KillObParser parse) {
        gameBuilder.getPlayerKiller(parse.getKillerID())
                .kill(GameTime.of(parse.getGameTime()),
                        gameBuilder.getPlayerInGame(parse.getKilledID()),
                        Mod.byModID(parse.getKilledModeID()));
    }

    private void processItem(Game.GameBuilder gameBuilder, ItemObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).addItem(Item.valueOf(parse.getItem()));
    }

    private void processClientBegin(Game.GameBuilder gameBuilder, ClientBeginParseObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).begin(GameTime.of(parse.getGameTime()));
    }

    private void processClientUserInfoChanged(Game.GameBuilder gameBuilder, ClientUserInfoChangedObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).changeInfos(parse.getName(), parse.getArguments());
    }

    private void processClientConnected(Game.GameBuilder gameBuilder, ClientConnectObParser parse) {
        gameBuilder.addPlayerInGame(new PlayerInGame(GameTime.of(parse.getGameTime()), parse.getId()));
    }
}
