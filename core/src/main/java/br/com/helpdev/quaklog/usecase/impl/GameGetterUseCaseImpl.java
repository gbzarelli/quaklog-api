package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.GameGetterUseCase;
import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;
import br.com.helpdev.quaklog.usecase.dto.mapper.GameMapper;
import br.com.helpdev.quaklog.usecase.dto.mapper.SimpleGamesMapper;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Named
class GameGetterUseCaseImpl implements GameGetterUseCase {

    private final GameRepository repository;
    private final SimpleGamesMapper simpleGamesMapper;
    private final GameMapper gameMapper;

    GameGetterUseCaseImpl(final GameRepository gameRepository,
                          final SimpleGamesMapper simpleGamesMapper,
                          final GameMapper gameMapper) {
        this.repository = gameRepository;
        this.simpleGamesMapper = simpleGamesMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public SimpleListGamesDTO getGamesByDate(final LocalDate date) {
        return simpleGamesMapper.toDTO(repository.getAllByDate(date));
    }

    @Override
    public Optional<GameDTO> getGameByUUID(final UUID gameUUID) {
        return repository.getByUUID(GameUUID.of(gameUUID))
                .map(gameMapper::toDTO);
    }
}
