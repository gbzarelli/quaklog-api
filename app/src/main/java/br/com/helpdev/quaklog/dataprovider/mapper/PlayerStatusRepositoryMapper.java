package br.com.helpdev.quaklog.dataprovider.mapper;

import br.com.helpdev.quaklog.dataprovider.entity.PlayerStatusEntity;
import br.com.helpdev.quaklog.entity.PlayerStatus;
import br.com.helpdev.quaklog.entity.vo.ConnectStatus;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class PlayerStatusRepositoryMapper {

    List<PlayerStatusEntity> toDatabaseEntity(final List<PlayerStatus> status) {
        return status.stream().map(PlayerStatusRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    PlayerStatusEntity toDatabaseEntity(final PlayerStatus status) {
        return PlayerStatusEntity.builder()
                .connectStatus(status.getStatus().name())
                .gameTime(status.getTime().toString())
                .build();
    }

    List<PlayerStatus> mapStatus(final List<PlayerStatusEntity> status) {
        return status.stream().map(PlayerStatusRepositoryMapper::mapStatus).collect(Collectors.toList());
    }

    PlayerStatus mapStatus(final PlayerStatusEntity status) {
        return PlayerStatus.builder()
                .status(ConnectStatus.valueOf(status.getConnectStatus()))
                .time(GameTime.of(status.getGameTime()))
                .build();
    }
}
