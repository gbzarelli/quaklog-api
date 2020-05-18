package br.com.helpdev.quaklog.dataprovider.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Document(collection = "game")
public class GameEntity {
    @Id
    private String uuid;
    private LocalDate date;
    private Map<String, String> gameParameters;
    private List<PlayerEntity> players;
    private PlayerEntity world;
    private String startGameTime;
    private String endGameTime;
    private int totalKills;
}
