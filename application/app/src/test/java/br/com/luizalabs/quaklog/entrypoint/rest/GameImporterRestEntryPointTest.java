package br.com.luizalabs.quaklog.entrypoint.rest;

import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.entrypoint.dto.GamesImportedDTO;
import br.com.luizalabs.quaklog.usecase.GameImporterUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameImporterRestEntryPointTest {

    private GameImporterUseCase importerUseCase = mock(GameImporterUseCase.class);
    private GameImporterRestEntryPoint gm = new GameImporterRestEntryPoint(importerUseCase);

    @Test
    void importGame() throws IOException {
        LocalDate localDate = LocalDate.now();
        InputStream inputStream = mock(InputStream.class);
        MultipartFile multipartFile = mock(MultipartFile.class);
        GamesImported gamesImported = loadGamesImportedTest();

        when(multipartFile.getInputStream()).thenReturn(inputStream);
        when(importerUseCase.importGame(localDate, inputStream)).thenReturn(gamesImported);

        ResponseEntity<GamesImportedDTO> response = gm.importGame(localDate, multipartFile);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(gamesImported.getGames().size(), Objects.requireNonNull(response.getBody()).getGamesIds().size());
        IntStream.range(0, gamesImported.getGames().size()).forEachOrdered(i -> assertEquals(gamesImported.getGames().get(i).toString(), response.getBody().getGamesIds().get(i)));
    }

    private GamesImported loadGamesImportedTest() {
        List<GameUUID> list = new ArrayList<>();
        list.add(GameUUID.create());
        list.add(GameUUID.create());
        return GamesImported.fromList(list);
    }
}