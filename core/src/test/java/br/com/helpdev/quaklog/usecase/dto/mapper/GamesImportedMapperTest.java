package br.com.helpdev.quaklog.usecase.dto.mapper;

import br.com.helpdev.quaklog.entity.GamesImported;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GamesImportedMapperTest {

    private GamesImportedMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new GamesImportedMapper();
    }

    @Test
    void shouldConversionWithoutNotificationsWithSuccess() {
        final var list = new ArrayList<GameUUID>();
        list.add(GameUUID.create());
        list.add(GameUUID.create());

        final var gamesImported = GamesImported.fromList(list);
        final var gamesImportedDTO = mapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(0, gamesImportedDTO.getNotifications().size());
        IntStream.range(0, gamesImported.getGames().size())
                .forEachOrdered(i -> assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString()));
    }

    @Test
    void shouldConversionWithNotificationsWithSuccess() {
        final var list = new ArrayList<GameUUID>();
        list.add(GameUUID.create());
        list.add(GameUUID.create());
        final var notifications = new String[]{"Failed load 1", "Failed load 2"};
        final var gamesImported = GamesImported.fromList(list, notifications);

        final var gamesImportedDTO = mapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(notifications.length, gamesImportedDTO.getNotifications().size());
        IntStream.range(0, gamesImported.getGames().size())
                .forEachOrdered(i -> assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString()));
        IntStream.range(0, gamesImportedDTO.getNotifications().size())
                .forEachOrdered(i -> assertEquals(gamesImportedDTO.getNotifications().get(i), notifications[i]));
    }
}