package br.com.helpdev.quaklog.entrypoint.mapper;

import br.com.helpdev.quaklog.entity.*;
import br.com.helpdev.quaklog.entrypoint.dto.GameDTO;
import br.com.helpdev.quaklog.entrypoint.dto.KillHistoryDTO;
import br.com.helpdev.quaklog.entrypoint.dto.PlayerDTO;
import br.com.helpdev.quaklog.entrypoint.dto.PlayerStatusDTO;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class GameMapper {

    public GameDTO toDTO(final Game game) {
        return GameDTO.builder()
                .startTime(game.getStartGameTime().toString())
                .endTime(game.getEndGameTime().toString())
                .parameters(game.getGameParameters())
                .totalKills(game.getTotalKills())
                .uuid(game.getGameUUID().toString())
                .word(mapPlayer(game.getWorld()))
                .players(mapPlayers(game.getPlayers()))
                .build();
    }

    private static List<PlayerDTO> mapPlayers(final List<Player> players) {
        return players.stream().map(GameMapper::mapPlayer).collect(Collectors.toList());
    }

    private static List<PlayerStatusDTO> mapStatus(final List<PlayerStatus> status) {
        return status.stream().map(GameMapper::mapStatus).collect(Collectors.toList());
    }

    private static List<KillHistoryDTO> mapKdHistory(final List<KillHistory> kdHistory) {
        return kdHistory.stream().map(GameMapper::mapKdHistory).collect(Collectors.toList());
    }

    private static PlayerDTO mapPlayer(final Player player) {
        return PlayerDTO.builder()
                .name(player.getName())
                .kills(player.getKills())
                .id(player.getId())
                .parameters(player.getParameters())
                .items(player.getItems().stream().map(Item::toString).collect(Collectors.toList()))
                .kdHistory(mapKdHistory(player.getKdHistory()))
                .status(mapStatus(player.getStatus()))
                .build();
    }


    private static PlayerStatusDTO mapStatus(final PlayerStatus status) {
        return PlayerStatusDTO.builder()
                .status(status.getStatus().name())
                .time(status.getTime().toString())
                .build();
    }

    private static KillHistoryDTO mapKdHistory(final KillHistory kdHistory) {
        return KillHistoryDTO.builder()
                .killMode(kdHistory.getKillMode().name())
                .mod(kdHistory.getMod().name())
                .player(kdHistory.getPlayer())
                .time(kdHistory.getGameTime().toString())
                .build();
    }
}
