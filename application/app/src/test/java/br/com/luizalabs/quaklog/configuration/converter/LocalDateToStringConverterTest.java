package br.com.luizalabs.quaklog.configuration.converter;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateToStringConverterTest {

    private final LocalDateToStringConverter converter = new LocalDateToStringConverter();

    @Test
    void convertNullObject() {
        final String convert = converter.convert(null);
        assertNull(convert);
    }

    @Test
    void converterWithSuccess() {
        final String convert = converter.convert(LocalDate.of(2010, 10, 10));
        assertEquals("2010-10-10", convert);
    }


}