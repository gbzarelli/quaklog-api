package br.com.helpdev.quaklog.configuration;

import br.com.helpdev.quaklog.configuration.converter.LocalDateToStringConverter;
import br.com.helpdev.quaklog.configuration.converter.StringToLocalDateConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoDbConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        final var list = Arrays.asList(
                new LocalDateToStringConverter(),
                new StringToLocalDateConverter()
        );
        return new MongoCustomConversions(list);
    }
}
