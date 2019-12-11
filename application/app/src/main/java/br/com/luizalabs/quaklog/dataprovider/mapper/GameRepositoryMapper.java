package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.GameEntity;
import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameRepositoryMapper {

    public static GameEntity toDatabaseEntity(Game game) {
        return new GameEntity(game.getGameUUID().toString(), game.getGameDate());
    }

    public static Game toDomainEntity(GameEntity gameEntity) {
        return new Game.GameBuilder(GameTime.of("00:00"), gameEntity.getDate())
                .setGameUUID(GameUUID.of(gameEntity.getUuid()))
                .build();
    }
}
