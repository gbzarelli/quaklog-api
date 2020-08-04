package br.com.helpdev.quaklog.usecase.dto.mapper;

import br.com.helpdev.quaklog.entity.*;
import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.KillHistoryDTO;
import br.com.helpdev.quaklog.usecase.dto.PlayerDTO;
import br.com.helpdev.quaklog.usecase.dto.PlayerStatusDTO;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
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

    private List<PlayerDTO> mapPlayers(final List<Player> players) {
        return players.stream().map(this::mapPlayer).collect(Collectors.toList());
    }

    private List<PlayerStatusDTO> mapStatus(final List<PlayerStatus> status) {
        return status.stream().map(this::mapStatus).collect(Collectors.toList());
    }

    private List<KillHistoryDTO> mapKdHistory(final List<KillHistory> kdHistory) {
        return kdHistory.stream().map(this::mapKdHistory).collect(Collectors.toList());
    }

    private PlayerDTO mapPlayer(final Player player) {
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


    private PlayerStatusDTO mapStatus(final PlayerStatus status) {
        return PlayerStatusDTO.builder()
                .status(status.getStatus().name())
                .time(status.getTime().toString())
                .build();
    }

    private KillHistoryDTO mapKdHistory(final KillHistory kdHistory) {
        return KillHistoryDTO.builder()
                .killMode(kdHistory.getKillMode().name())
                .mod(kdHistory.getMod().name())
                .player(kdHistory.getPlayer())
                .time(kdHistory.getGameTime().toString())
                .build();
    }
}
