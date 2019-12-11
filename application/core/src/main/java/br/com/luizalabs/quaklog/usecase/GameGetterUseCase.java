package br.com.luizalabs.quaklog.usecase;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;

import java.time.LocalDate;
import java.util.List;

public interface GameGetterUseCase {

    List<Game> getGamesByDate(LocalDate date);

    Game getGameByUUID(GameUUID gameUUID);

}
