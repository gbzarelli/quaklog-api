package br.com.luizalabs.quaklog.entrypoint.mapper;

import br.com.luizalabs.quaklog.entity.Game;
import br.com.luizalabs.quaklog.entrypoint.dto.GameDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameMapper {
    public static GameDTO toDTO(Game game) {
        return null;
    }
}
