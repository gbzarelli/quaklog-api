package br.com.helpdev.quaklog.entrypoint;

import br.com.helpdev.quaklog.entrypoint.dto.GameDTO;
import br.com.helpdev.quaklog.entrypoint.dto.SimpleListGamesDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface GameGetterEntryPoint {
    SimpleListGamesDTO searchGameByDate(LocalDate fileDate);

    Optional<GameDTO> getGameByUUID(String uuid);
}
