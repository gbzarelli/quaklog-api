package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamesImportedTest {

    @Test
    void createFromListWithNotification() {
        String[] notifications = new String[]{"Failed load 1", "Failed load 2"};
        GamesImported empty = GamesImported.empty(notifications);
        assertEquals(0, empty.getGames().size());
        assertEquals(notifications.length, empty.getNotifications().size());
        for (String notification : notifications) {
            assertTrue(empty.getNotifications().contains(notification));
        }
    }

    @Test
    void createFromListWithoutNotification() {
        List<GameUUID> games = new ArrayList<>();
        games.add(GameUUID.create());
        games.add(GameUUID.create());

        GamesImported imported = GamesImported.fromList(games);
        assertEquals(games.size(), imported.getGames().size());

        IntStream.range(0, games.size()).forEachOrdered(i -> assertEquals(games.get(i).toString(), imported.getGames().get(i).toString()));
    }

}