package br.com.helpdev.quaklog.entrypoint;

import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface GameGetterEntryPoint {
    SimpleListGamesDTO searchGameByDate(LocalDate fileDate);

    Optional<GameDTO> getGameByUUID(UUID uuid);
}
