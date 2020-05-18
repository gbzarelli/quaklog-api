package br.com.helpdev.quaklog.dataprovider.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KillHistoryEntity {
    private String gameTime;
    private String killMode;
    private int playerID;
    private int modID;
}
