package com.github.jaime.translator.series;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.impl.ParserException;

public class TestAPIMode {

    @Test
    void shouldReturnAPIMode() {
        assertEquals(APIMode.SPELLING, APIMode.valueOf("spelling".toUpperCase()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Spelling", "  spelling", "SPELLING", "SpElLing  "})
    void allShouldReturnApiModeSpelling(String value) throws ParserException {
        assertEquals(APIMode.SPELLING, APIMode.parse(value));
    }

    @Test
    void unknownValueShouldThrowException() {
        assertThrows(ParserException.class, () -> APIMode.parse("random"));
    }

    @Test
    void shouldReturnBetaMode() throws ParserException {
        assertEquals(APIMode.BETA, APIMode.parse("beta"));
    }
}
