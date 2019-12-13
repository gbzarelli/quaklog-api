package br.com.luizalabs.quaklog.dataprovider.mapper;

import br.com.luizalabs.quaklog.dataprovider.entity.GameEntity;
import br.com.luizalabs.quaklog.dataprovider.entity.PlayerEntity;
import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.PlayerInGame;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class GameRepositoryMapper {

    public static GameEntity toDatabaseEntity(final Game game) {
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

    public static Game toDomainEntity(final GameEntity gameEntity) {
        return new Game.GameBuilder(GameTime.of(gameEntity.getStartGameTime()), gameEntity.getDate())
                .setGameUUID(GameUUID.of(gameEntity.getUuid()))
                .setTotalKills(gameEntity.getTotalKills())
                .setEndGameTime(GameTime.of(gameEntity.getEndGameTime()))
                .setGameParameters(gameEntity.getGameParameters())
                .setWorld(PlayerRepositoryMapper.mapWorld(gameEntity.getWorld()))
                .setPlayers(mapPlayers(gameEntity.getPlayers()))
                .build();

    }

    private static Map<Integer, PlayerInGame> mapPlayers(final List<PlayerEntity> players) {
        val map = new HashMap<Integer, PlayerInGame>();
        players.forEach(playerEntity -> map.put(playerEntity.getId(), PlayerRepositoryMapper.mapPlayer(playerEntity)));
        return map;
    }


}
