package com.github.jaime.translator.series;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.impl.ParserException;

public class TestLanguages {

    @ParameterizedTest
    @ValueSource(strings = { "us", "uS", "  us", "US", "US  " })
    void shouldReturnAppropriateLanguage(String value) throws ParserException {
        assertEquals(Language.AMERICAN, Language.parse(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"es", "us", "gb", "fr", "de"})
    void allShouldReturnALanguage(String value) throws ParserException {
        assertEquals(Language.class, Language.parse(value).getClass());
    }

    @Test
    void shouldThrowExceptionIfUnknownLanguage() {
        assertThrows(ParserException.class, () -> {
            Language.parse("random");
        });
    }

}
