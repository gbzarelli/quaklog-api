package br.com.helpdev.quaklog.processor.impl;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.Item;
import br.com.helpdev.quaklog.entity.PlayerInGame;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.Mod;
import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.GameParserKey;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.GameParseProcessor;
import br.com.helpdev.quaklog.processor.parser.objects.*;


import javax.inject.Named;
import java.time.LocalDate;

@Named
class GameParseProcessorImpl implements GameParseProcessor {

    @Override
    public Game.GameBuilder initGame(final LocalDate gameDate,
                                     final Parsable<InitGameObParser> gameParser,
                                     final String line) throws GameParserException {
        final var initGameObParser = gameParser.parse(line);
        final var gameTime = GameTime.of(initGameObParser.getGameTime());
        return new Game.GameBuilder(gameTime, gameDate).setGameParameters(initGameObParser.getArguments());
    }

    @Override
    public void processLine(final Game.GameBuilder gameBuilder,
                            final GameParserKey parserKey,
                            final String line) throws GameParserException {
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
                break;
        }
    }

    private void processShutdownGame(final Game.GameBuilder gameBuilder,
                                     final ShutdownGameObParser parse) {
        gameBuilder.setEndGameTime(GameTime.of(parse.getGameTime()));
    }

    private void processClientDisconnected(final Game.GameBuilder gameBuilder,
                                           final ClientDisconnectObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).disconnect(GameTime.of(parse.getGameTime()));
    }

    private void processKill(final Game.GameBuilder gameBuilder,
                             final KillObParser parse) {
        gameBuilder.getPlayerKiller(parse.getKillerID())
                .kill(GameTime.of(parse.getGameTime()),
                        gameBuilder.getPlayerInGame(parse.getKilledID()),
                        Mod.byModID(parse.getKilledModeID()));
    }

    private void processItem(final Game.GameBuilder gameBuilder,
                             final ItemObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).addItem(Item.valueOf(parse.getItem()));
    }

    private void processClientBegin(final Game.GameBuilder gameBuilder,
                                    final ClientBeginParseObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).begin(GameTime.of(parse.getGameTime()));
    }

    private void processClientUserInfoChanged(final Game.GameBuilder gameBuilder,
                                              final ClientUserInfoChangedObParser parse) {
        gameBuilder.getPlayerInGame(parse.getId()).changeInfos(parse.getName(), parse.getArguments());
    }

    private void processClientConnected(final Game.GameBuilder gameBuilder,
                                        final ClientConnectObParser parse) {
        gameBuilder.addPlayerInGame(new PlayerInGame(GameTime.of(parse.getGameTime()), parse.getId()));
    }
}
