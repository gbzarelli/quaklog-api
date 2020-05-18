package br.com.helpdev.quaklog.usecase;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;

import java.time.LocalDate;
import java.util.List;

public interface GameGetterUseCase {

    List<Game> getGamesByDate(LocalDate date);

    Game getGameByUUID(GameUUID gameUUID);

}
