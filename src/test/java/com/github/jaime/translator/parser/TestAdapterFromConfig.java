package com.github.jaime.translator.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.exception.impl.validation.MessageValidation;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.series.Language;

public class TestAdapterFromConfig {

    private static ConfigAdapter config;
    private TranslateFromConfig adapter = new TranslateFromConfig(config);

    @BeforeAll
    static void setUp() {
        config = mock(ConfigAdapter.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "key", ":fx-key", ":fx key" })
    void shouldThrowInvalidKeyException(String wrongKey) {
        when(config.getApiKey()).thenReturn(wrongKey);
        assertThrows(InvalidKeyException.class, () -> adapter.getApiKey());
    }

    @Test
    void shouldBeValidKey() throws InvalidKeyException {
        String key = "public:fx";
        when(config.getApiKey()).thenReturn(key);
        assertEquals("DeepL-Auth-Key " + key, adapter.getApiKey());
    }

    @Test
    void shouldGetDefaultTranslationLanguage() throws ValidationException, ParserException {
        when(config.getTargetLanguage()).thenReturn(null);
        assertEquals(Language.BRITISH, adapter.getTargetLanguage());
    }

    @Test
    void shouldGetLanguage() throws APIException{
        when(config.getTargetLanguage()).thenReturn(Language.AMERICAN);
        assertEquals(Language.AMERICAN, adapter.getTargetLanguage());

    }

    @Test
    void shouldGetDefaultFromLanguage() throws APIException {
        when(config.getFromLanguage()).thenReturn(null);
        assertEquals(Language.SPANISH, adapter.getFromLanguage());
    }

    @Test
    void shouldCastToProperException() {
        assertThrows(ValidationException.class, () -> {
            when(config.getTargetLanguage()).thenThrow(ParserException.class);
            adapter.getTargetLanguage();
        });
    }

    private static Stream<Arguments> languageProvider() {
        return Stream.of(Arguments.of(Language.AMERICAN), Arguments.of(Language.BRITISH));
    }

    @ParameterizedTest
    @MethodSource("languageProvider")
    void shouldGetDefaultEnglish(Language value) throws APIException {
        when(config.getFromLanguage()).thenReturn(value);
        assertEquals(Language.ENGLISH, adapter.getFromLanguage());
    }

    @Test
    void shouldReturnInputLanguage() throws APIException {
        when(config.getFromLanguage()).thenReturn(Language.DEUTSCH);
        assertEquals(Language.DEUTSCH, adapter.getFromLanguage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "  " })
    void shouldThrowEmptyException(String text) {
        when(config.getTextToTranslate()).thenReturn(text);
        assertThrows(MessageValidation.class, () -> adapter.getMessage());
    }

    private static Stream<Arguments> textProvider() {
        return Stream.of(Arguments.of("Hello", "Hello"), Arguments.of("Hello", " Hello"),
                Arguments.of("Hello", "Hello "), Arguments.of("HeLlO", "HeLlO")

        );
    }

    @ParameterizedTest
    @MethodSource("textProvider")
    void shouldCleanWhiteSpacesAndMaintainFormat(String expected, String input)
            throws ValidationException {
        when(config.getTextToTranslate()).thenReturn(input);
        assertEquals(expected, adapter.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " " })
    void shouldThrowParserExceptionIfEmptyApiKey(String key) {
        when(config.getApiKey()).thenReturn(key);
        assertThrows(InvalidKeyException.class, () -> adapter.getApiKey());
    }

    @Test
    void shouldThrowExceptionIfNull() {
        when(config.getApiKey()).thenReturn(null);
        assertThrows(InvalidKeyException.class, () -> adapter.getApiKey());
    }

    @Test
    void shouldThrowExceptionIfNullMessage() {
        when(config.getTextToTranslate()).thenReturn(null);
        assertThrows(MessageValidation.class, () -> adapter.getMessage());
    }

}
