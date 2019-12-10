package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class ClientConnectObParser implements ParseObject {
    @NonNull
    private String gameTime;
    @NonNull
    private Integer id;
}
