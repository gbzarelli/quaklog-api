package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.GameEntity;
import br.com.luizalabs.quaklog.entity.Game;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameRepositoryMapper {

    public static GameEntity toDatabaseEntity(Game game) {
        //TODO
        return null;
    }

    public static Game toDomainEntity(GameEntity gameEntity) {
        //TODO
        return null;
    }
}
