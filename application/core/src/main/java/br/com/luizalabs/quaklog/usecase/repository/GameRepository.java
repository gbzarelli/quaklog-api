package br.com.luizalabs.quaklog.usecase.repository;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entity.vo.GameUUID;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository {
    void save(Game game);

    List<Game> getAllByDate(LocalDate localDate);

    Game getByUUID(GameUUID gameUUID);
}
