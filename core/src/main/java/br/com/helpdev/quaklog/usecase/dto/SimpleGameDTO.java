package br.com.helpdev.quaklog.usecase.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class SimpleGameDTO {
    @JsonIgnore
    private String uuid;
    @JsonProperty("total_kills")
    private Integer totalKills;
    private List<SimplePlayerDTO> players;

    @Builder
    public SimpleGameDTO(String uuid, Integer totalKills, List<SimplePlayerDTO> players) {
        this.uuid = uuid;
        this.totalKills = totalKills;
        this.players = players;
    }
}
