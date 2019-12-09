package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.parser.GameParserKeys;
import br.com.luizalabs.quaklog.usecase.GameImporterUseCase;
import lombok.val;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Named
class GameImporterUseCaseImpl implements GameImporterUseCase {

    @Override
    public GamesImported importGame(LocalDate gameDate, InputStream inputStream) throws IOException {
        try (val reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return importGame(reader);
        }
    }

    private GamesImported importGame(BufferedReader reader) throws IOException {
        while (reader.ready()) {
            val line = reader.readLine();
            val gameParserOptional = GameParserKeys.getParserByText(line);
            if (gameParserOptional.isPresent()) {
//                val gameParser = gameParserOptional.get();
//                gameParser.parsable.parse(line);
            }
        }

        return GamesImported.empty();
    }

}
