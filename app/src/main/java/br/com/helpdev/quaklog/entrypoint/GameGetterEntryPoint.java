package br.com.helpdev.quaklog.entrypoint;

import br.com.helpdev.quaklog.entrypoint.dto.GameDTO;
import br.com.helpdev.quaklog.entrypoint.dto.SimpleListGamesDTO;

import java.time.LocalDate;

public interface GameGetterEntryPoint {
    SimpleListGamesDTO searchGameByDate(LocalDate fileDate);

    GameDTO getGameByUUID(String uuid);
}
