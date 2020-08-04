package br.com.helpdev.quaklog.usecase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KillHistoryDTO {
    private final String time;
    private final String killMode;
    private final Integer player;
    private final String mod;
}
