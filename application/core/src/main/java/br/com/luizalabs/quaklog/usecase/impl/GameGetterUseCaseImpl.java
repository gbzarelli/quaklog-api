package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.usecase.GameGetterUseCase;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
class GameGetterUseCaseImpl implements GameGetterUseCase {

    private final GameRepository repository;

    GameGetterUseCaseImpl(GameRepository gameRepository) {
        this.repository = gameRepository;
    }

    @Override
    public List<Game> getGamesByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    @Override
    public Game getGameByUUID(GameUUID gameUUID) {
        return repository.getByUUID(gameUUID);
    }
}
