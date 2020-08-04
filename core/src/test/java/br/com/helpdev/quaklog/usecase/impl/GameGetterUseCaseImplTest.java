package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.dto.GameDTO;
import br.com.helpdev.quaklog.usecase.dto.SimpleListGamesDTO;
import br.com.helpdev.quaklog.usecase.dto.mapper.GameMapper;
import br.com.helpdev.quaklog.usecase.dto.mapper.SimpleGamesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GameGetterUseCaseImplTest {

    @Mock
    private GameRepository repository;

    @Mock
    private SimpleGamesMapper simpleGamesMapper;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameGetterUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldGetSimpleListGamesByDateWithSuccess() {
        final var date = LocalDate.now();
        final var gameList = new ArrayList<Game>();
        final var simpleListGame = mock(SimpleListGamesDTO.class);

        when(repository.getAllByDate(date))
                .thenReturn(gameList);
        when(simpleGamesMapper.toDTO(gameList))
                .thenReturn(simpleListGame);

        final var response = useCase.getGamesByDate(date);

        assertEquals(simpleListGame, response);
    }

    @Test
    void shouldGetGameByUUIDWithSuccess() {
        final var uuid = UUID.randomUUID();
        final var game = mock(Game.class);
        final var gameDto = mock(GameDTO.class);

        when(repository.getByUUID(GameUUID.of(uuid)))
                .thenReturn(Optional.ofNullable(game));
        when(gameMapper.toDTO(game))
                .thenReturn(gameDto);

        final var response = useCase.getGameByUUID(uuid);

        assertEquals(gameDto, response.get());
    }

    @Test
    void shouldReturnEmptyWhenGetGameByUUIDDontReturnValue() {
        final var uuid = UUID.randomUUID();

        when(repository.getByUUID(GameUUID.of(uuid)))
                .thenReturn(Optional.empty());

        final var response = useCase.getGameByUUID(uuid);

        assertEquals(Optional.empty(), response);
    }

}