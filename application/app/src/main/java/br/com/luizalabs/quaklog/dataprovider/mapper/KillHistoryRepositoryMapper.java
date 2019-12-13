package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.KillHistoryEntity;
import br.com.luizalabs.quaklog.entity.KillHistory;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.KillMode;
import br.com.luizalabs.quaklog.entity.vo.Mod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
