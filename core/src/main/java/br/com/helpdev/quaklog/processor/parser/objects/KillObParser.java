package br.com.helpdev.quaklog.processor.parser.objects;

import br.com.helpdev.quaklog.processor.parser.ParseObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class KillObParser implements ParseObject {

    public static final int WORLD_USER_ID = 1022;

    @NonNull
    private String gameTime;
    @NonNull
    private Integer killerID;
    @NonNull
    private Integer killedID;
    @NonNull
    private Integer killedModeID;
    @NonNull
    private String killed;
    @NonNull
    private String killer;
    @NonNull
    private String killedMode;
}
