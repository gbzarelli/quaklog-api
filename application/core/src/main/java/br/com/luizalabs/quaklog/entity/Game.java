package br.com.luizalabs.quaklog.entity;

import br.com.luizalabs.quaklog.entity.vo.GameUUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Game extends Notifiable {

    private GameUUID gameUUID = new GameUUID();
    private LocalDate importDate;

    public static class GameBuilder {
        private Game game = new Game();

        public GameBuilder addNotification(String notification) {
            game.addNotification(notification);
            return this;
        }

        public GameBuilder importDate(LocalDate localDate) {
            game.importDate = localDate;
            return this;
        }

        public Game build() {
            //TODO CHECK
            return game;
        }
    }
}
