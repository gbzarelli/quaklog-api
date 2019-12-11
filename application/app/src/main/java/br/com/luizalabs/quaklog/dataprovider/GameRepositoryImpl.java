package br.com.luizalabs.quaklog.dataprovider;

import br.com.luizalabs.quaklog.dataprovider.entity.GameEntity;
import br.com.luizalabs.quaklog.dataprovider.mapper.GameRepositoryMapper;
import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
class GameRepositoryImpl implements GameRepository {

    private final GameRepositoryMongo mongo;

    public GameRepositoryImpl(GameRepositoryMongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void save(Game game) {
        GameEntity gameEntity = GameRepositoryMapper.toDatabaseEntity(game);
        mongo.save(gameEntity);
    }

    @Override
    public List<Game> getAllByDate(LocalDate localDate) {
        val entities = mongo.findAllByDate(localDate);
        return entities.stream().map(GameRepositoryMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Game getByUUID(GameUUID gameUUID) {
        Optional<GameEntity> gameEntity = mongo.findById(gameUUID.toString());
        return gameEntity.map(GameRepositoryMapper::toDomainEntity).orElse(null);
    }
}
