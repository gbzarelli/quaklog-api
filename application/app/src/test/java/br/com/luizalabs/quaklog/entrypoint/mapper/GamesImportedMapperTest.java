package br.com.luizalabs.quaklog.entrypoint.mapper;

import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import br.com.luizalabs.quaklog.entrypoint.dto.GamesImportedDTO;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GamesImportedMapperTest {

    @Test
    void conversion_without_notifications_with_success() {
        ArrayList<GameUUID> list = new ArrayList<>();
        list.add(new GameUUID());
        list.add(new GameUUID());

        GamesImported gamesImported = GamesImported.fromList(list);
        GamesImportedDTO gamesImportedDTO = GamesImportedMapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(0, gamesImportedDTO.getNotifications().size());
        for (int i = 0; i < gamesImported.getGames().size(); i++) {
            assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString());
        }
    }

    @Test
    void conversion_with_notifications_with_success() {
        ArrayList<GameUUID> list = new ArrayList<>();
        list.add(new GameUUID());
        list.add(new GameUUID());
        String[] notifications = new String[]{"Failed load 1", "Failed load 2"};
        GamesImported gamesImported = GamesImported.fromList(list, notifications);

        GamesImportedDTO gamesImportedDTO = GamesImportedMapper.toDTO(gamesImported);

        assertEquals(list.size(), gamesImportedDTO.getGamesIds().size());
        assertEquals(notifications.length, gamesImportedDTO.getNotifications().size());
        for (int i = 0; i < gamesImported.getGames().size(); i++) {
            assertEquals(gamesImportedDTO.getGamesIds().get(i), list.get(i).toString());
        }
        for (int i = 0; i < gamesImportedDTO.getNotifications().size(); i++) {
            assertEquals(gamesImportedDTO.getNotifications().get(i), notifications[i]);
        }
    }
}