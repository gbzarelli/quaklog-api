package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.usecase.GameGetterUseCase;
import br.com.luizalabs.quaklog.usecase.repository.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class GameGetterUseCaseImplTest {
    private final GameRepository repository = mock(GameRepository.class);
    private final GameGetterUseCase useCase = new GameGetterUseCaseImpl(repository);

    @Test
    void shouldGetGamesByDateWithSuccess() {
        LocalDate localDate = LocalDate.now();
        List<Game> gamesMock = Mockito.mock(List.class);
        when(repository.getAllByDate(localDate)).thenReturn(gamesMock);

        final List<Game> gameList = useCase.getGamesByDate(localDate);
        Assertions.assertEquals(gamesMock, gameList);
    }

    @Test
    void shouldGetSingleGameWithSuccess() {
        GameUUID gameUUID = mock(GameUUID.class);
        Game gameMock = Mockito.mock(Game.class);
        when(repository.getByUUID(gameUUID)).thenReturn(gameMock);

        final Game game = useCase.getGameByUUID(gameUUID);
        Assertions.assertEquals(gameMock, game);
    }
}