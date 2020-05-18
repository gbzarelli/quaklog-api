package br.com.helpdev.quaklog.entrypoint.mapper;

import br.com.helpdev.quaklog.entrypoint.dto.GamesImportedDTO;
import br.com.helpdev.quaklog.entity.GamesImported;
import br.com.helpdev.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GamesImportedMapperTest {

    @Test
    void shouldConversionWithoutNotificationsWithSuccess() {
        ArrayList<GameUUID> list = new ArrayList<>();
        list.add(GameUUID.create());
        list.add(GameUUID.create());

        GamesImported gamesImported = GamesImported.fromList(list);
        GamesImportedDTO gamesImportedDTO = GamesImportedMapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(0, gamesImportedDTO.getNotifications().size());
        IntStream.range(0, gamesImported.getGames().size()).forEachOrdered(i -> assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString()));
    }

    @Test
    void shouldConversionWithNotificationsWithSuccess() {
        ArrayList<GameUUID> list = new ArrayList<>();
        list.add(GameUUID.create());
        list.add(GameUUID.create());
        String[] notifications = new String[]{"Failed load 1", "Failed load 2"};
        GamesImported gamesImported = GamesImported.fromList(list, notifications);

        GamesImportedDTO gamesImportedDTO = GamesImportedMapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(notifications.length, gamesImportedDTO.getNotifications().size());
        IntStream.range(0, gamesImported.getGames().size()).forEachOrdered(i -> assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString()));
        IntStream.range(0, gamesImportedDTO.getNotifications().size()).forEachOrdered(i -> assertEquals(gamesImportedDTO.getNotifications().get(i), notifications[i]));
    }
}