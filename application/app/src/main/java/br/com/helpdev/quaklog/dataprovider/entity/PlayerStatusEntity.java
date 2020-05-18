package br.com.helpdev.quaklog.dataprovider.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStatusEntity {
    private String gameTime;
    private String connectStatus;
}
