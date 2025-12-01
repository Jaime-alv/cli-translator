package com.github.jaime.translator.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public class TestConfigAdapter {

    private static CommandLineService mockCMD;
    private ConfigAdapterFromCMD configAdapterFromCMD = new ConfigAdapterFromCMD(mockCMD);

    @BeforeAll
    static void setUp() {
        mockCMD = mock(CommandLineService.class);
    }

    @Test
    void shouldReturnDefaultLanguageIfEmpty() throws ParserException {
        when(mockCMD.getTargetLanguage()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getTargetLanguage());
    }

    @Test
    void shouldThrowAExceptionIfNotValid() {
        when(mockCMD.getTargetLanguage()).thenReturn("random");
        assertThrows(ParserException.class, () -> configAdapterFromCMD.getTargetLanguage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "es", "  es", "ES", "es   " })
    void allShouldBeValid(String lang) throws ParserException {
        when(mockCMD.getTargetLanguage()).thenReturn(lang);
        assertEquals(Language.class, configAdapterFromCMD.getTargetLanguage().getClass());
    }

    @Test
    void shouldReturnDefaultFromLanguageIfEmpty() throws ParserException {
        when(mockCMD.getFromLanguage()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getFromLanguage());
    }

    @Test
    void shouldThrowAExceptionIfNotValidFromLanguage() {
        when(mockCMD.getFromLanguage()).thenReturn("random");
        assertThrows(ParserException.class, () -> configAdapterFromCMD.getFromLanguage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "es", "  es", "ES", "es   " })
    void allShouldBeValidForFromLang(String lang) throws ParserException {
        when(mockCMD.getFromLanguage()).thenReturn(lang);
        assertEquals(Language.class, configAdapterFromCMD.getFromLanguage().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = { "spelling", "translate" })
    void allShouldBeValidApiModes(String mode) throws ParserException {
        when(mockCMD.getApiMode()).thenReturn(mode);
        assertEquals(APIMode.class, configAdapterFromCMD.getApiMode().getClass());
    }
}
