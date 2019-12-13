package br.com.luizalabs.quaklog.configuration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(@Nullable final LocalDate source) {
        if (source == null) return null;
        return DateFormatUtils.DATE_FORMATTER.format(source);
    }
}
