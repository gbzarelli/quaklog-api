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
    private int killerID;
    @NonNull
    private int killedID;
    @NonNull
    private int killedModeID;
    @NonNull
    private String killed;
    @NonNull
    private String killer;
    @NonNull
    private String killedMode;
}
