package br.com.luizalabs.quaklog.entity.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor()
public class GameTime {
    private final String time;
    //TODO CHANGE TO TIME
    @Override
    public String toString() {
        return time;
    }
}
