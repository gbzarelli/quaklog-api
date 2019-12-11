package br.com.luizalabs.quaklog.configuration;

import br.com.luizalabs.quaklog.configuration.converter.LocalDateToStringConverter;
import br.com.luizalabs.quaklog.configuration.converter.StringToLocalDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDbConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter> list = new ArrayList<>();
        list.add(new LocalDateToStringConverter());
        list.add(new StringToLocalDateConverter());
        return new MongoCustomConversions(list);
    }
}
