package br.com.helpdev.quaklog.entrypoint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStatusDTO {
    private String time;
    private String status;
}
