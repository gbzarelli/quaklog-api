package br.com.helpdev.quaklog.entrypoint.rest;

import br.com.helpdev.quaklog.configuration.SwaggerConfig;
import br.com.helpdev.quaklog.entrypoint.GameGetterEntryPoint;
import br.com.helpdev.quaklog.usecase.GameGetterUseCase;
import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;
import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(RestConstants.PATH_GAME)
@Api(tags = SwaggerConfig.TAG_GAME_ENTRY_POINT)
class GameGetterRestEntryPoint implements GameGetterEntryPoint {

    private final GameGetterUseCase useCase;

    GameGetterRestEntryPoint(GameGetterUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    @GetMapping("/date/{date}")
    public SimpleListGamesDTO searchGameByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {
        return useCase.getGamesByDate(date);
    }


    @GetMapping(value = "/{uuid}")
    public ResponseEntity<GameDTO> getGameByUUIDEndpoint(@PathVariable final String uuid) {
        return getGameByUUID(UUID.fromString(uuid))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Optional<GameDTO> getGameByUUID(final UUID uuid) {
        return useCase.getGameByUUID(uuid);

    }
}
