package br.com.luizalabs.quaklog.usecase;

import br.com.luizalabs.quaklog.entity.GamesImported;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public interface GameImporterUseCase {
    GamesImported importGame(LocalDate gameDate, InputStream inputStream) throws IOException;
}
