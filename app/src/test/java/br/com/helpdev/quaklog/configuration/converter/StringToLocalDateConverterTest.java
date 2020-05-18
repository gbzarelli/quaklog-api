package br.com.helpdev.quaklog.configuration.converter;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class StringToLocalDateConverterTest {
    private final StringToLocalDateConverter converter = new StringToLocalDateConverter();

    @Test
    void shouldNotThrowExceptionWhenConvertingNullParameter() {
        final LocalDate convert = converter.convert(null);
        assertNull(convert);
    }

    @Test
    void shouldConverterWithSuccess() {
        final LocalDate convert = converter.convert("2010-10-10");
        assertEquals(LocalDate.of(2010, 10, 10), convert);
    }

    @Test
    void shouldThrowDateTimeParseExceptionWhenTryingToConvertInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> converter.convert("201s0-10-10"));
    }

}