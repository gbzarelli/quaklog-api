package br.com.luizalabs.quaklog.dataprovider.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "game")
public class GameEntity {
    @Id
    private String uuid;
    private LocalDate date;
    //TODO
}
