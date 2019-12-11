package br.com.luizalabs.quaklog.entrypoint;

import br.com.luizalabs.quaklog.entrypoint.dto.GameDTO;
import br.com.luizalabs.quaklog.entrypoint.dto.SimpleListGamesDTO;

import java.time.LocalDate;

public interface GameGetterEntryPoint {
    SimpleListGamesDTO searchGameByDate(LocalDate fileDate);

    GameDTO getGameByUUID(String uuid);
}
