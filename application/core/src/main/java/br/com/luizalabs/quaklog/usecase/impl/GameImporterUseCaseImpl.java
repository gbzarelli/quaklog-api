package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.parser.GameParserKey;
import br.com.luizalabs.quaklog.processor.GameParseProcessor;
import br.com.luizalabs.quaklog.usecase.GameImporterUseCase;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;
import lombok.val;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

@Named
class GameImporterUseCaseImpl implements GameImporterUseCase {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private final GameParseProcessor parseProcessor;
    private final GameRepository gameRepository;

    @Inject
    public GameImporterUseCaseImpl(GameParseProcessor parseProcessor, GameRepository gameRepository) {
        this.parseProcessor = parseProcessor;
        this.gameRepository = gameRepository;
    }

    @Override
    public GamesImported importGame(LocalDate gameDate, InputStream inputStream) throws IOException {
        try (val reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return importGame(gameDate, reader);
        }
    }

    private GamesImported importGame(LocalDate gameDate, BufferedReader reader) throws IOException {
        val importNotifications = new ArrayList<String>();
        val gamesImported = new ArrayList<GameUUID>();
        Game.GameBuilder gameBuilder = null;

        var numberLine = 0;

        while (reader.ready()) {
            numberLine++;
            val line = reader.readLine();
            val keyExtracted = GameParserKey.getParserByText(line);

            if (keyExtracted.isEmpty()) {
                val message = logKeyNotFound(gameBuilder, numberLine, line);
                importNotifications.add(message);
                continue;
            }

            try {
                val parserKey = keyExtracted.get();

                if (isShutdownKeyAndGameIsRunning(gameBuilder, parserKey)) {
                    parseProcessor.processLine(gameBuilder, parserKey, line);
                    gamesImported.add(buildAndPersistGame(gameBuilder));
                    gameBuilder = null;
                } else if (isInitGameKey(parserKey)) {
                    if (isGameRunning(gameBuilder)) {
                        gamesImported.add(buildAndPersistGame(gameBuilder));
                    }
                    gameBuilder = parseProcessor.initGame(gameDate, parserKey.getParsable(), line);
                } else if (isGameRunning(gameBuilder)) {
                    parseProcessor.processLine(gameBuilder, parserKey, line);
                } else {
                    throw new IllegalArgumentException("No games are running and receive: {" + parserKey.key + "} with value: {" + line + "}");
                }

            } catch (Exception e) {
                val message = logException(gameBuilder, numberLine, e);
                importNotifications.add(message);
            }
        }

        return GamesImported.fromList(gamesImported, importNotifications);
    }

    private boolean isInitGameKey(GameParserKey parserKey) {
        return parserKey == GameParserKey.INIT_GAME;
    }

    private boolean isShutdownKeyAndGameIsRunning(Game.GameBuilder gameBuilder, GameParserKey parserKey) {
        return isShutdownKey(parserKey) && isGameRunning(gameBuilder);
    }

    private boolean isShutdownKey(GameParserKey parserKey) {
        return parserKey == GameParserKey.SHUTDOWN_GAME;
    }

    private GameUUID buildAndPersistGame(Game.GameBuilder gameBuilder) {
        val game = gameBuilder.build();
        gameRepository.save(game);
        return game.getGameUUID();
    }

    private boolean isGameRunning(Game.GameBuilder gameBuilder) {
        return gameBuilder != null;
    }

    private String logKeyNotFound(Game.GameBuilder gameBuilder, int numberLine, String line) {
        val message = numberLine + " - KEY not found to parse {" + line + "}";
        logger.warning(message);
        addGameLog(gameBuilder, message);
        return message;
    }

    private String logException(Game.GameBuilder gameBuilder, int numberLine, Exception e) {
        val message = numberLine + " - " + e.getMessage();
        logger.severe(message);
        addGameLog(gameBuilder, message);
        return message;
    }

    private void addGameLog(Game.GameBuilder gameBuilder, String message) {
        if (isGameRunning(gameBuilder)) {
            gameBuilder.addNotification(message);
        }
    }
}
