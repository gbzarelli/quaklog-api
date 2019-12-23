package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ItemObParser implements ParseObject {
    @NonNull
    private String gameTime;
    @NonNull
    private Integer id;
    @NonNull
    private String item;
}
