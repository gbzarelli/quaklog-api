package br.com.luizalabs.quaklog.usecase.repository;

import br.com.luizalabs.quaklog.entity.Game;

public interface GameRepository {
    void save(Game game);
}
