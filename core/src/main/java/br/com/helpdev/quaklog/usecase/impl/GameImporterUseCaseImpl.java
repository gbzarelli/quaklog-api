package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.GamesImported;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.processor.GameParseProcessor;
import br.com.helpdev.quaklog.processor.parser.GameParserKey;
import br.com.helpdev.quaklog.usecase.GameImporterUseCase;
import br.com.helpdev.quaklog.usecase.dto.GamesImportedDTO;
import br.com.helpdev.quaklog.usecase.dto.mapper.GamesImportedMapper;

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
    private final GamesImportedMapper mapper;

    @Inject
    public GameImporterUseCaseImpl(final GameParseProcessor parseProcessor,
                                   final GameRepository gameRepository,
                                   final GamesImportedMapper mapper) {
        this.parseProcessor = parseProcessor;
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    @Override
    public GamesImportedDTO importGame(final LocalDate gameDate,
                                       final InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return executeImportGame(gameDate, reader);
        }
    }

    private GamesImportedDTO executeImportGame(final LocalDate gameDate,
                                               final BufferedReader reader) throws IOException {
        final var importNotifications = new ArrayList<String>();
        final var gameUUIDS = new ArrayList<GameUUID>();
        Game.GameBuilder gameBuilder = null;

        var numberLine = 0;

        while (reader.ready()) {
            numberLine++;
            final var line = reader.readLine();
            final var keyExtracted = GameParserKey.getParserByText(line);

            if (keyExtracted.isEmpty()) {
                final var message = logKeyNotFound(gameBuilder, numberLine, line);
                importNotifications.add(message);
                continue;
            }

            try {
                final var parserKey = keyExtracted.get();

                if (isShutdownKeyAndGameIsRunning(gameBuilder, parserKey)) {
                    parseProcessor.processLine(gameBuilder, parserKey, line);
                    gameUUIDS.add(buildAndPersistGame(gameBuilder));
                    gameBuilder = null;
                } else if (isInitGameKey(parserKey)) {
                    if (isGameRunning(gameBuilder)) {
                        gameUUIDS.add(buildAndPersistGame(gameBuilder));
                    }
                    gameBuilder = parseProcessor.initGame(gameDate, parserKey.getParsable(), line);
                } else if (isGameRunning(gameBuilder)) {
                    parseProcessor.processLine(gameBuilder, parserKey, line);
                } else {
                    throw new IllegalArgumentException("No games are running and receive: {" + parserKey.key + "} with value: {" + line + "}");
                }

            } catch (Exception e) {
                final var message = logException(gameBuilder, numberLine, e);
                importNotifications.add(message);
            }
        }

        final var gamesImported = GamesImported.fromList(gameUUIDS, importNotifications);

        return mapper.toDTO(gamesImported);
    }

    private boolean isInitGameKey(final GameParserKey parserKey) {
        return parserKey == GameParserKey.INIT_GAME;
    }

    private boolean isShutdownKeyAndGameIsRunning(final Game.GameBuilder gameBuilder,
                                                  final GameParserKey parserKey) {
        return isShutdownKey(parserKey) && isGameRunning(gameBuilder);
    }

    private boolean isShutdownKey(final GameParserKey parserKey) {
        return parserKey == GameParserKey.SHUTDOWN_GAME;
    }

    private GameUUID buildAndPersistGame(final Game.GameBuilder gameBuilder) {
        final var game = gameBuilder.build();
        gameRepository.save(game);
        return game.getGameUUID();
    }

    private boolean isGameRunning(final Game.GameBuilder gameBuilder) {
        return gameBuilder != null;
    }

    private String logKeyNotFound(final Game.GameBuilder gameBuilder,
                                  final int numberLine,
                                  final String line) {
        final var message = numberLine + " - KEY not found to parse {" + line + "}";
        logger.warning(message);
        addGameLog(gameBuilder, message);
        return message;
    }

    private String logException(final Game.GameBuilder gameBuilder,
                                final int numberLine,
                                final Exception e) {
        final var message = numberLine + " - " + e.getMessage();
        logger.severe(message);
        addGameLog(gameBuilder, message);
        return message;
    }

    private void addGameLog(final Game.GameBuilder gameBuilder,
                            final String message) {
        if (isGameRunning(gameBuilder)) {
            gameBuilder.addNotification(message);
        }
    }
}
