package br.com.helpdev.quaklog.usecase.dto.mapper;

import br.com.helpdev.quaklog.entity.GamesImported;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.dto.GamesImportedDTO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Named
public class GamesImportedMapper {
    public GamesImportedDTO toDTO(final GamesImported gamesImported) {
        return new GamesImportedDTO(
                gamesImported.getGames().stream().map(GameUUID::toString).collect(Collectors.toList()),
                new ArrayList<>(gamesImported.getNotifications())
        );
    }
}
