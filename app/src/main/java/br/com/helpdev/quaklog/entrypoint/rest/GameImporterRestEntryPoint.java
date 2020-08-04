package br.com.helpdev.quaklog.entrypoint.rest;

import br.com.helpdev.quaklog.configuration.SwaggerConfig;
import br.com.helpdev.quaklog.entrypoint.GameImporterEntryPoint;
import br.com.helpdev.quaklog.usecase.GameImporterUseCase;
import br.com.helpdev.quaklog.usecase.dto.GamesImportedDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(RestConstants.PATH_GAME)
@Api(tags = SwaggerConfig.TAG_GAME_ENTRY_POINT)
class GameImporterRestEntryPoint implements GameImporterEntryPoint<MultipartFile, ResponseEntity<GamesImportedDTO>> {

    private final GameImporterUseCase gameImporterUseCase;

    @Autowired
    GameImporterRestEntryPoint(GameImporterUseCase gameImporterUseCase) {
        this.gameImporterUseCase = gameImporterUseCase;
    }

    @PostMapping
    @Override
    public ResponseEntity<GamesImportedDTO> importGame(@RequestHeader(RestConstants.REQUEST_HEADER_LOGFILE_DATE)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fileDate,
                                                       @RequestParam("file") MultipartFile file) throws IOException {
        final var gamesImported = gameImporterUseCase.importGame(fileDate, file.getInputStream());
        final var uri = getGameImportedUri(fileDate);
        return ResponseEntity.created(uri).body(gamesImported);
    }

    private URI getGameImportedUri(LocalDate fileDate) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/date/{gameDate}").buildAndExpand(fileDate).toUri();
    }
}
