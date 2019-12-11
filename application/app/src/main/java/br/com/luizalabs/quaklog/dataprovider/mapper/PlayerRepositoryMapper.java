package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.PlayerEntity;
import br.com.luizalabs.quaklog.entity.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerRepositoryMapper {

    public static List<PlayerEntity> toDatabaseEntity(Collection<Player> players) {
        return players.stream().map(PlayerRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    public static PlayerEntity toDatabaseEntity(Player player) {
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
}
