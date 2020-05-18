package br.com.helpdev.quaklog.entrypoint.mapper;

import br.com.helpdev.quaklog.entrypoint.dto.GamesImportedDTO;
import br.com.helpdev.quaklog.entity.GamesImported;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.stream.Collectors;

@UtilityClass
public class GamesImportedMapper {
    public static GamesImportedDTO toDTO(final GamesImported gamesImported) {
        return new GamesImportedDTO(
                gamesImported.getGames().stream().map(GameUUID::toString).collect(Collectors.toList()),
                new ArrayList<>(gamesImported.getNotifications())
        );
    }
}
