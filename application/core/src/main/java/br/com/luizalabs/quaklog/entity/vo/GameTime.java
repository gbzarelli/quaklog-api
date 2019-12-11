package br.com.luizalabs.quaklog.entity.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameTime {

    public static GameTime parse(String time) {
        return new GameTime(time);
    }

    private final String time;

    @Override
    public String toString() {
        return time;
    }
}
