package br.com.helpdev.quaklog.usecase.impl;

import br.com.helpdev.quaklog.dataprovider.GameRepository;
import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import br.com.helpdev.quaklog.usecase.GameGetterUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameGetterUseCaseImplTest {
    private final GameRepository repository = mock(GameRepository.class);
    private final GameGetterUseCase useCase = new GameGetterUseCaseImpl(repository);

    @Test
    void shouldGetGamesByDateWithSuccess() {
        LocalDate localDate = LocalDate.now();
        @SuppressWarnings("unchecked")
        List<Game> gamesMock = Mockito.mock(List.class);
        when(repository.getAllByDate(localDate)).thenReturn(gamesMock);

        final List<Game> gameList = useCase.getGamesByDate(localDate);
        Assertions.assertEquals(gamesMock, gameList);
    }

    @Test
    void shouldGetSingleGameWithSuccess() {
        GameUUID gameUUID = mock(GameUUID.class);
        Game gameMock = Mockito.mock(Game.class);
        when(repository.getByUUID(gameUUID)).thenReturn(Optional.of(gameMock));

        var game = useCase.getGameByUUID(gameUUID);
        Assertions.assertEquals(gameMock, game.get());
    }
}