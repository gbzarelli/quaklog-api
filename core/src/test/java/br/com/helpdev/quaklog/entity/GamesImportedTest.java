package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.GameUUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamesImportedTest {

    @Test
    void shouldCreateGamesImportedFromListWithNotification() {
        final var notifications = new String[]{"Failed load 1", "Failed load 2"};
        final var empty = GamesImported.empty(notifications);
        assertEquals(0, empty.getGames().size());
        assertEquals(notifications.length, empty.getNotifications().size());
        for (String notification : notifications) {
            assertTrue(empty.getNotifications().contains(notification));
        }
    }

    @Test
    void shouldCreateGamesImportedFromListWithoutNotification() {
        final var games = new ArrayList<GameUUID>();
        games.add(GameUUID.create());
        games.add(GameUUID.create());

        final var imported = GamesImported.fromList(games);
        assertEquals(games.size(), imported.getGames().size());

        IntStream.range(0, games.size()).forEachOrdered(i -> Assertions.assertEquals(games.get(i).toString(), imported.getGames().get(i).toString()));
    }

}