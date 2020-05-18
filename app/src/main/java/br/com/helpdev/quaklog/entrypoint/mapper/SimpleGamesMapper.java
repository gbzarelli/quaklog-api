package br.com.helpdev.quaklog.entrypoint.mapper;

import br.com.helpdev.quaklog.entrypoint.dto.SimpleGameDTO;
import br.com.helpdev.quaklog.entrypoint.dto.SimpleListGamesDTO;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.Player;
import br.com.helpdev.quaklog.entrypoint.dto.SimplePlayerDTO;
import lombok.experimental.UtilityClass;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class SimpleGamesMapper {

    public static SimpleListGamesDTO toDTO(final List<Game> games) {
        final var collect = games.stream().map(SimpleGamesMapper::toSimpleGame)
                .collect(Collectors.toMap(SimpleGameDTO::getUuid, Function.identity()));
        return SimpleListGamesDTO.builder().games(collect).build();
    }

    private static SimpleGameDTO toSimpleGame(final Game game) {
        return SimpleGameDTO.builder()
                .totalKills(game.getTotalKills())
                .uuid(game.getGameUUID().toString())
                .players(mapPlayers(game))
                .build();
    }

    private static List<SimplePlayerDTO> mapPlayers(final Game game) {
        return game.getPlayers().stream().map(SimpleGamesMapper::mapPlayer).collect(Collectors.toList());
    }

    private static SimplePlayerDTO mapPlayer(final Player player) {
        return SimplePlayerDTO.builder()
                .id(player.getId())
                .kills(player.getKills())
                .name(player.getName())
                .build();
    }
}
