package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.PlayerStatusEntity;
import br.com.luizalabs.quaklog.entity.PlayerStatus;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatusRepositoryMapper {

    public static List<PlayerStatusEntity> toDatabaseEntity(List<PlayerStatus> status) {
        return status.stream().map(PlayerStatusRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    public static PlayerStatusEntity toDatabaseEntity(PlayerStatus status) {
        return PlayerStatusEntity.builder()
                .connectStatus(status.getStatus().name())
                .gameTime(status.getTime().toString())
                .build();
    }
}
