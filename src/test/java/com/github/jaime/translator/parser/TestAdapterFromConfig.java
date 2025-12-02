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

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.series.Language;

public class TestAdapterFromConfig {

    private static Config config;
    private TranslateFromConfig adapter = new TranslateFromConfig(config);

    @BeforeAll
    static void setUp() {
        config = mock(Config.class);
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
    void shouldGetDefaultTranslationLanguage() {
        when(config.getTarget()).thenReturn(Optional.empty());
        assertEquals(Language.BRITISH, adapter.getTargetLanguage());
    }

    @Test
    void shouldGetLanguage() {
        when(config.getTarget()).thenReturn(Optional.of(Language.AMERICAN));
        assertEquals(Language.AMERICAN, adapter.getTargetLanguage());

    }

    @Test
    void shouldGetDefaultFromLanguage() {
        when(config.getFrom()).thenReturn(Optional.empty());
        assertEquals(Language.SPANISH, adapter.getFromLanguage());
    }

    private static Stream<Arguments> languageProvider() {
        return Stream.of(Arguments.of(Language.AMERICAN), Arguments.of(Language.BRITISH));
    }

    @ParameterizedTest
    @MethodSource("languageProvider")
    void shouldGetDefaultEnglish(Language value) {
        when(config.getFrom()).thenReturn(Optional.of(value));
        assertEquals(Language.ENGLISH, adapter.getFromLanguage());
    }

    @Test
    void shouldReturnInputLanguage() {
        when(config.getFrom()).thenReturn(Optional.of(Language.DEUTSCH));
        assertEquals(Language.DEUTSCH, adapter.getFromLanguage());
    }
}
