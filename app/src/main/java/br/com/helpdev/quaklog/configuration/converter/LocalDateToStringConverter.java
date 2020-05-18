package br.com.helpdev.quaklog.configuration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(@Nullable final LocalDate source) {
        if (isNull(source)) return null;
        return DateFormatUtils.DATE_FORMATTER.format(source);
    }
}
