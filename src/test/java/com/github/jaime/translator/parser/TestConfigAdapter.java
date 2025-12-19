package com.github.jaime.translator.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @ParameterizedTest
    @ValueSource(strings = { "es", "  es", "ES", "es   ", "random" })
    void allShouldBeValid(String lang) throws ParserException {
        when(mockCMD.getTargetLanguage()).thenReturn(lang);
        assertEquals(Language.class, configAdapterFromCMD.getTargetLanguage().getClass());
    }

    @Test
    void shouldReturnDefaultFromLanguageIfEmpty() throws ParserException {
        when(mockCMD.getFromLanguage()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getFromLanguage());
    }


    @ParameterizedTest
    @ValueSource(strings = { "es", "  es", "ES", "es   ", "other" })
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

    @ParameterizedTest
    @ValueSource(strings = { "", "  ", "key", "valid:fx" })
    void shouldReturnAnyKey(String emptyKey) {
        when(mockCMD.getApiKey()).thenReturn(emptyKey);
        assertTrue(configAdapterFromCMD.getApiKey().getClass().equals(String.class));
    }

    @Test
    void shouldAcceptNullValue() {
        when(mockCMD.getApiKey()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getApiKey());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "  ", "key", "valid:fx" })
    void shouldReturnAnyMessage(String message) {
        when(mockCMD.getMessage()).thenReturn(message);
        assertTrue(configAdapterFromCMD.getTextToTranslate().getClass().equals(String.class));
    }

    @Test
    void shouldAcceptNullValueForMessage() {
        when(mockCMD.getMessage()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getTextToTranslate());
    }

    @Test
    void shouldReturnContextAsIs() {
        when(mockCMD.getContext()).thenReturn(null);
        assertEquals(null, configAdapterFromCMD.getContext());
    }

    @Test
    void shouldReturnContextIfNotEmpty() {
        String message = "Random DAta";
        when(mockCMD.getContext()).thenReturn(message);
        assertEquals(message, configAdapterFromCMD.getContext());
    }

    @Test
    void shouldReturnAString() {
        when(mockCMD.getApiMode()).thenReturn("translate");
        when(mockCMD.getFromLanguage()).thenReturn("source");
        when(mockCMD.getTargetLanguage()).thenReturn("target");
        assertEquals(String.class, configAdapterFromCMD.toString().getClass());
    }

    

}
