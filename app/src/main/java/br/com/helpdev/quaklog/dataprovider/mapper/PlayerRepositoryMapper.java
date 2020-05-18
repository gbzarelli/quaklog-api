package br.com.helpdev.quaklog.dataprovider.mapper;

import br.com.helpdev.quaklog.dataprovider.entity.PlayerEntity;
import br.com.helpdev.quaklog.entity.Player;
import br.com.helpdev.quaklog.entity.PlayerInGame;
import br.com.helpdev.quaklog.entity.World;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class PlayerRepositoryMapper {

    List<PlayerEntity> toDatabaseEntity(final Collection<Player> players) {
        return players.stream().map(PlayerRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    PlayerEntity toDatabaseEntity(final Player player) {
        return PlayerEntity.builder()
                .id(player.getId())
                .items(ItemRepositoryMapper.toDatabaseEntity(player.getItems()))
                .kills(player.getKills())
                .name(player.getName())
                .parameters(player.getParameters())
                .kdHistory(KillHistoryRepositoryMapper.toDatabaseEntity(player.getKdHistory()))
                .status(PlayerStatusRepositoryMapper.toDatabaseEntity(player.getStatus()))
                .build();
    }

    PlayerInGame mapPlayer(final PlayerEntity playerEntity) {
        return PlayerInGame.builder()
                .kills(playerEntity.getKills())
                .kdHistory(KillHistoryRepositoryMapper.mapKdHistory(playerEntity.getKdHistory()))
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .parameters(playerEntity.getParameters())
                .status(PlayerStatusRepositoryMapper.mapStatus(playerEntity.getStatus()))
                .items(ItemRepositoryMapper.mapItems(playerEntity.getItems()))
                .build();
    }

    World mapWorld(final PlayerEntity world) {
        return World.builder()
                .kills(world.getKills())
                .kdHistory(KillHistoryRepositoryMapper.mapKdHistory(world.getKdHistory()))
                .parameters(world.getParameters())
                .status(PlayerStatusRepositoryMapper.mapStatus(world.getStatus()))
                .items(ItemRepositoryMapper.mapItems(world.getItems()))
                .build();
    }


}
