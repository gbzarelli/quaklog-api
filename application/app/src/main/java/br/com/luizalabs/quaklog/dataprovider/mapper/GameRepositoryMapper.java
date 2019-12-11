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
        return GameEntity.builder()
                .date(game.getGameDate())
                .endGameTime(game.getEndGameTime() == null ? null : game.getEndGameTime().toString())
                .gameParameters(game.getGameParameters())
                .players(PlayerRepositoryMapper.toDatabaseEntity(game.getPlayers()))
                .startGameTime(game.getStartGameTime().toString())
                .uuid(game.getGameUUID().toString())
                .world(PlayerRepositoryMapper.toDatabaseEntity(game.getWorld()))
                .totalKills(game.getTotalKills())
                .build();
    }

    public static Game toDomainEntity(GameEntity gameEntity) {
        return new Game.GameBuilder(GameTime.of("00:00"), gameEntity.getDate())
                .setGameUUID(GameUUID.of(gameEntity.getUuid()))
                .build();
    }
}
