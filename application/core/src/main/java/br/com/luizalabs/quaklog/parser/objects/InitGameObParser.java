package br.com.luizalabs.quaklog.parser.objects;

import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Data
@Builder
public class InitGameObParser implements ParseObject {
    @NonNull
    private String gameTime;
    @NonNull
    private Map<String, String> arguments;
}
