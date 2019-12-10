package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
