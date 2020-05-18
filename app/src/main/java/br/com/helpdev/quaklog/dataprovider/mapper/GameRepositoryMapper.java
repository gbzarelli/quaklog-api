package br.com.helpdev.quaklog.dataprovider.mapper;

import br.com.helpdev.quaklog.dataprovider.entity.GameEntity;
import br.com.helpdev.quaklog.dataprovider.entity.PlayerEntity;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.PlayerInGame;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class GameRepositoryMapper {

    public GameEntity toDatabaseEntity(final Game game) {
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

    public Game toDomainEntity(final GameEntity gameEntity) {
        return new Game.GameBuilder(GameTime.of(gameEntity.getStartGameTime()), gameEntity.getDate())
                .setGameUUID(GameUUID.of(gameEntity.getUuid()))
                .setTotalKills(gameEntity.getTotalKills())
                .setEndGameTime(GameTime.of(gameEntity.getEndGameTime()))
                .setGameParameters(gameEntity.getGameParameters())
                .setWorld(PlayerRepositoryMapper.mapWorld(gameEntity.getWorld()))
                .setPlayers(mapPlayers(gameEntity.getPlayers()))
                .build();

    }

    private Map<Integer, PlayerInGame> mapPlayers(final List<PlayerEntity> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        PlayerEntity::getId,
                        PlayerRepositoryMapper::mapPlayer,
                        (a, b) -> b,
                        HashMap::new
                        )
                );
    }


}
