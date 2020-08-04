package br.com.helpdev.quaklog.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class GameDTO {
    private String uuid;
    @JsonProperty("total_kills")
    private Integer totalKills;
    private PlayerDTO word;
    private List<PlayerDTO> players;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    private Map<String, String> parameters;
}
