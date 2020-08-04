package br.com.helpdev.quaklog.processor.parser.objects;

import br.com.helpdev.quaklog.processor.parser.ParseObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ClientBeginParseObParser implements ParseObject {
    @NonNull
    private String gameTime;
    @NonNull
    private Integer id;
}
