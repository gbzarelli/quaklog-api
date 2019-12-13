package br.com.luizalabs.quaklog.entrypoint.mapper;

import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.entrypoint.dto.GamesImportedDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
