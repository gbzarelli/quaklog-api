package br.com.helpdev.quaklog.usecase;

import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface GameGetterUseCase {

    SimpleListGamesDTO getGamesByDate(LocalDate date);

    Optional<GameDTO> getGameByUUID(UUID gameUUID);

}
