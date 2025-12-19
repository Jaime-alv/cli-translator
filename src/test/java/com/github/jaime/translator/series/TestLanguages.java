package com.github.jaime.translator.series;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestLanguages {

    @ParameterizedTest
    @ValueSource(strings = { "us", "uS", "  us", "US", "US  " })
    void shouldReturnAppropriateLanguage(String value) {
        assertEquals(Language.AMERICAN, Language.parse(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"es", "us", "gb", "fr", "de"})
    void allShouldReturnALanguage(String value) {
        assertEquals(Language.class, Language.parse(value).getClass());
    }

    @Test
    void shouldSetOtherLanguage() {
        Language random = Language.parse("random");
        assertEquals("RANDOM", random.value);
    }

}
