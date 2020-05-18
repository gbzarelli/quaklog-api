package br.com.helpdev.quaklog.configuration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(@Nullable final String source) {
        if (source == null) return null;
        return LocalDate.parse(source, DateFormatUtils.DATE_FORMATTER);
    }
}
