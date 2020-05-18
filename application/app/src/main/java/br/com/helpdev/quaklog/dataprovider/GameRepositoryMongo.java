package br.com.helpdev.quaklog.dataprovider;

import br.com.helpdev.quaklog.dataprovider.entity.GameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameRepositoryMongo extends MongoRepository<GameEntity, String> {
    List<GameEntity> findAllByDate(LocalDate date);
}
