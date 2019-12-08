package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GamesImported extends Notifiable {

    public static GamesImported fromList(List<GameUUID> gamesUUIDs, String... notifications) {
        val game = new GamesImported(gamesUUIDs);
        Arrays.stream(notifications).forEach(game::addNotification);
        return game;
    }

    public static GamesImported empty(String... notifications) {
        val game = new GamesImported(Collections.emptyList());
        Arrays.stream(notifications).forEach(game::addNotification);
        return game;
    }

    @Getter
    private List<GameUUID> games;
}
