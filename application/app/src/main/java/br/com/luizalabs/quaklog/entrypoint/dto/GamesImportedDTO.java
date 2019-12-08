package br.com.luizalabs.quaklog.entrypoint.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
public class GamesImportedDTO {
    private List<String> gamesIds;
    private List<String> notifications;
}
