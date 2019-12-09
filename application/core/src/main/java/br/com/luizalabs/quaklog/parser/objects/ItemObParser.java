package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class ItemObParser implements ParseObject {
    @NonNull
    private String gameTime;
    @NonNull
    private int id;
    @NonNull
    private String item;
}
