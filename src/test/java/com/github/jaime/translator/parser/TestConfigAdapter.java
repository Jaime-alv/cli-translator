package com.github.jaime.translator.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest
    @ValueSource(strings = { "", "  " })
    void shouldThrowEmptyException(String text) {
        when(mockCMD.getMessage()).thenReturn(text);
        assertThrows(ParserException.class, () -> configAdapterFromCMD.getMessage());
    }

    private static Stream<Arguments> textProvider() {
        return Stream.of(Arguments.of("Hello", "Hello"), Arguments.of("Hello", "  Hello"),
                Arguments.of("Hello", "Hello   "), Arguments.of("HeLlO", "HeLlO")

        );
    }

    @ParameterizedTest
    @MethodSource("textProvider")
    void shouldCleanWhiteSpacesAndMaintainFormat(String expected, String input)
            throws ParserException {
        when(mockCMD.getMessage()).thenReturn(input);
        assertEquals(expected, configAdapterFromCMD.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "   " })
    void shouldThrowParserExceptionIfEmptyApiKey(String key) {
        when(mockCMD.getApiKey()).thenReturn(key);
        assertThrows(ParserException.class, () -> configAdapterFromCMD.getAPIKey());
    }

    @ParameterizedTest
    @ValueSource(strings = { "  key", "key", "key   ", "key", " key    " })
    void shouldTrimApiKey(String key) throws ParserException {
        when(mockCMD.getApiKey()).thenReturn(key);
        assertEquals("key", configAdapterFromCMD.getAPIKey());
    }

    @ParameterizedTest
    @ValueSource(strings = { "k e y", "k e y ", "k e y  ", " k e y", "   k e y", "  k e y  " })
    void shouldMaintainInternalWhiteSpaces(String key) throws ParserException {
        when(mockCMD.getApiKey()).thenReturn(key);
        assertEquals("k e y", configAdapterFromCMD.getAPIKey());
    }
}
