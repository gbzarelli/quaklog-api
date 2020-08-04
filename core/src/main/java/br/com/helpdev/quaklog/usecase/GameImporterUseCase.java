package br.com.helpdev.quaklog.usecase;

import br.com.helpdev.quaklog.usecase.dto.GamesImportedDTO;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public interface GameImporterUseCase {
    GamesImportedDTO importGame(LocalDate gameDate, InputStream inputStream) throws IOException;
}
