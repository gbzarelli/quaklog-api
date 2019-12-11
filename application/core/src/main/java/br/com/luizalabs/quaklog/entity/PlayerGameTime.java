package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.ConnectStatus;
import br.com.luizalabs.quaklog.entity.vo.GameTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerGameTime {

    private final GameTime time;
    private final ConnectStatus status;

    static PlayerGameTime newConnectedTime(GameTime time) {
        return new PlayerGameTime(time, ConnectStatus.CONNECTED);
    }

    static PlayerGameTime newDisconnectedTime(GameTime time) {
        return new PlayerGameTime(time, ConnectStatus.DISCONNECTED);
    }

    static PlayerGameTime newBeginTime(GameTime time) {
        return new PlayerGameTime(time, ConnectStatus.BEGIN);
    }
}
