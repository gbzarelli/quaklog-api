package br.com.helpdev.quaklog.usecase.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class SimpleListGamesDTO {
    private Map<String, SimpleGameDTO> games;
    private Integer quantity;

    @Builder
    public SimpleListGamesDTO(Map<String, SimpleGameDTO> games) {
        this.games = games;
        this.quantity = games.size();
    }
}
