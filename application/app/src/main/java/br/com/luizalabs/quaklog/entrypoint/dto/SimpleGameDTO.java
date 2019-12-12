package br.com.luizalabs.quaklog.entrypoint.dto;


import br.com.luizalabs.quaklog.entrypoint.rest.RestConstants;
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
    @JsonProperty("detail_path")
    private String detailPath;

    @Builder
    public SimpleGameDTO(String uuid, Integer totalKills, List<SimplePlayerDTO> players) {
        this.uuid = uuid;
        this.totalKills = totalKills;
        this.players = players;
        this.detailPath = String.format("/%s/%s", RestConstants.PATH_GAME, uuid);
    }
}
