package br.com.helpdev.quaklog.usecase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimplePlayerDTO {
    private Integer id;
    private String name;
    private Integer kills;
}
