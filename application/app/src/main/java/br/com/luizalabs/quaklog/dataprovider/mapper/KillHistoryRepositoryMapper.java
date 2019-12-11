package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.KillHistoryEntity;
import br.com.luizalabs.quaklog.entity.KillHistory;

import java.util.List;
import java.util.stream.Collectors;

public class KillHistoryRepositoryMapper {
    public static List<KillHistoryEntity> toDatabaseEntity(List<KillHistory> kdHistory) {
        return kdHistory.stream().map(KillHistoryRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    public static KillHistoryEntity toDatabaseEntity(KillHistory kdHistory) {
        return KillHistoryEntity.builder()
                .gameTime(kdHistory.getGameTime().toString())
                .killMode(kdHistory.getKillMode().name())
                .modID(kdHistory.getMod().id)
                .playerID(kdHistory.getPlayer())
                .build();
    }
}
