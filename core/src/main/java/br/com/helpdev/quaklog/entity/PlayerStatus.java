package br.com.helpdev.quaklog.entity;

import br.com.helpdev.quaklog.entity.vo.ConnectStatus;
import br.com.helpdev.quaklog.entity.vo.GameTime;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PlayerStatus {

    private final GameTime time;
    private final ConnectStatus status;

    static PlayerStatus newConnectedTime(final GameTime time) {
        return new PlayerStatus(time, ConnectStatus.CONNECTED);
    }

    static PlayerStatus newDisconnectedTime(final GameTime time) {
        return new PlayerStatus(time, ConnectStatus.DISCONNECTED);
    }

    static PlayerStatus newBeginTime(final GameTime time) {
        return new PlayerStatus(time, ConnectStatus.BEGIN);
    }
}
