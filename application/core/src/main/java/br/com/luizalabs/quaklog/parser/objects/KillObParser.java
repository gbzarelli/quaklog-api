package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class KillObParser implements ParseObject {

    public static final int WORLD_USER_ID = 1022;

    private int killerID;
    private int killedID;
    private int killedModeID;

    private String killed;
    private String killer;
    private String killedMode;
}
