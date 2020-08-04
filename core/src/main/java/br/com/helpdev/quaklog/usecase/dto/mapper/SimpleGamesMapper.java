package br.com.helpdev.quaklog.usecase.dto.mapper;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.Player;
import br.com.helpdev.quaklog.usecase.dto.SimpleGameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;
import br.com.helpdev.quaklog.usecase.dto.SimplePlayerDTO;

import javax.inject.Named;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class SimpleGamesMapper {

    public SimpleListGamesDTO toDTO(final List<Game> games) {
        final var collect = games.stream()
                .map(this::toSimpleGame)
                .collect(Collectors.toMap(SimpleGameDTO::getUuid, Function.identity()));
        return SimpleListGamesDTO.builder().games(collect).build();
    }

    public SimpleGameDTO toSimpleGame(final Game game) {
        return SimpleGameDTO.builder()
                .totalKills(game.getTotalKills())
                .uuid(game.getGameUUID().toString())
                .players(mapPlayers(game))
                .build();
    }

    private List<SimplePlayerDTO> mapPlayers(final Game game) {
        return game.getPlayers().stream().map(this::mapPlayer).collect(Collectors.toList());
    }

    private SimplePlayerDTO mapPlayer(final Player player) {
        return SimplePlayerDTO.builder()
                .id(player.getId())
                .kills(player.getKills())
                .name(player.getName())
                .build();
    }
}
