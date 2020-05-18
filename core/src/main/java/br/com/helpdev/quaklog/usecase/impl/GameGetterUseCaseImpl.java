package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.GameGetterUseCase;

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
