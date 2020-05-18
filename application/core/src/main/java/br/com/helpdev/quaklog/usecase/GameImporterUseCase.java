package br.com.helpdev.quaklog.usecase;

import br.com.helpdev.quaklog.entity.GamesImported;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public interface GameImporterUseCase {
    GamesImported importGame(LocalDate gameDate, InputStream inputStream) throws IOException;
}
