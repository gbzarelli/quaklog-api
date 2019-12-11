package br.com.luizalabs.quaklog.configuration.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DateFormatUtils.DATE_FORMATTER);
    }
}
