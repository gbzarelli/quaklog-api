package br.com.helpdev.quaklog.dataprovider.mapper;

import br.com.helpdev.quaklog.dataprovider.entity.KillHistoryEntity;
import br.com.helpdev.quaklog.entity.KillHistory;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.KillMode;
import br.com.helpdev.quaklog.entity.vo.Mod;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class KillHistoryRepositoryMapper {

    static List<KillHistoryEntity> toDatabaseEntity(final List<KillHistory> kdHistory) {
        return kdHistory.stream().map(KillHistoryRepositoryMapper::toDatabaseEntity).collect(Collectors.toList());
    }

    static KillHistoryEntity toDatabaseEntity(final KillHistory kdHistory) {
        return KillHistoryEntity.builder()
                .gameTime(kdHistory.getGameTime().toString())
                .killMode(kdHistory.getKillMode().name())
                .modID(kdHistory.getMod().id)
                .playerID(kdHistory.getPlayer())
                .build();
    }

    static List<KillHistory> mapKdHistory(final List<KillHistoryEntity> kdHistory) {
        return kdHistory.stream().map(KillHistoryRepositoryMapper::mapKdHistory).collect(Collectors.toList());
    }

    static KillHistory mapKdHistory(final KillHistoryEntity kdHistory) {
        return KillHistory.builder()
                .gameTime(GameTime.of(kdHistory.getGameTime()))
                .killMode(KillMode.valueOf(kdHistory.getKillMode()))
                .player(kdHistory.getPlayerID())
                .mod(Mod.byModID(kdHistory.getModID()))
                .build();
    }

}
