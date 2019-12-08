package br.com.luizalabs.quaklog.entrypoint.rest;

import br.com.luizalabs.quaklog.configuration.SwaggerConfig;
import br.com.luizalabs.quaklog.entrypoint.GameImporter;
import br.com.luizalabs.quaklog.entrypoint.dto.GamesImportedDTO;
import br.com.luizalabs.quaklog.entrypoint.mapper.GamesImportedMapper;
import br.com.luizalabs.quaklog.usecase.GameImporterUseCase;
import io.swagger.annotations.Api;
import lombok.val;
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
class GameImporterRestEntryPoint implements GameImporter<MultipartFile, ResponseEntity<GamesImportedDTO>> {

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
        val gamesImported = gameImporterUseCase.importGame(fileDate, file.getInputStream());
        val uri = getGameImportedUri(fileDate);
        return ResponseEntity.created(uri).body(GamesImportedMapper.toDTO(gamesImported));
    }

    private URI getGameImportedUri(LocalDate fileDate) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/date/{gameDate}").buildAndExpand(fileDate).toUri();
    }
}
