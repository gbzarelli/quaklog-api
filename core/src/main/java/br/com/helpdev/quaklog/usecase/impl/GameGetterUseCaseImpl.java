package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.GameGetterUseCase;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Named
class GameGetterUseCaseImpl implements GameGetterUseCase {

    private final GameRepository repository;

    GameGetterUseCaseImpl(final GameRepository gameRepository) {
        this.repository = gameRepository;
    }

    @Override
    public List<Game> getGamesByDate(final LocalDate date) {
        return repository.getAllByDate(date);
    }

    @Override
    public Optional<Game> getGameByUUID(final GameUUID gameUUID) {
        return repository.getByUUID(gameUUID);
    }
}
