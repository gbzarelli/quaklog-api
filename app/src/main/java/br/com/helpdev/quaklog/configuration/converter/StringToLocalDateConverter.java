package br.com.helpdev.quaklog.configuration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(@Nullable final String source) {
        if (isNull(source)) return null;
        return LocalDate.parse(source, DateFormatUtils.DATE_FORMATTER);
    }
}
