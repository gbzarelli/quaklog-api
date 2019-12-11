package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.PlayerStatusEntity;
import br.com.luizalabs.quaklog.entity.PlayerStatus;
import br.com.luizalabs.quaklog.entity.vo.ConnectStatus;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PlayerStatusRepositoryMapper {

    static List<PlayerStatusEntity> toDatabaseEntity(List<PlayerStatus> status) {
        return status.stream().map(PlayerStatusRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    static PlayerStatusEntity toDatabaseEntity(PlayerStatus status) {
        return PlayerStatusEntity.builder()
                .connectStatus(status.getStatus().name())
                .gameTime(status.getTime().toString())
                .build();
    }

    static List<PlayerStatus> mapStatus(List<PlayerStatusEntity> status) {
        return status.stream().map(PlayerStatusRepositoryMapper::mapStatus).collect(Collectors.toList());
    }

    static PlayerStatus mapStatus(PlayerStatusEntity status) {
        return PlayerStatus.builder()
                .status(ConnectStatus.valueOf(status.getConnectStatus()))
                .time(GameTime.of(status.getGameTime()))
                .build();
    }
}
