package br.com.luizalabs.quaklog.dataprovider;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryMongo implements GameRepository {
    @Override
    public void save(Game game) {

    }
}
