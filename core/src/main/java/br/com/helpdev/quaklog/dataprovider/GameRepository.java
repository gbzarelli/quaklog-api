package br.com.helpdev.quaklog.dataprovider;

import br.com.helpdev.quaklog.entity.Game;
import br.com.helpdev.quaklog.entity.vo.GameUUID;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository {
    void save(Game game);

    List<Game> getAllByDate(LocalDate localDate);

    Game getByUUID(GameUUID gameUUID);
}
