package com.github.jaime.translator.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.impl.InvalidKeyException;

public class TestQuotaAdapter {

    private Config config = mock(Config.class);
    private QuotaFromConfig quotaFromConfig = new QuotaFromConfig(config);

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", ":fx r", "adf:fx:09"})
    void shouldThrowException(String wrongKey) {
        when(config.getApiKey()).thenReturn(wrongKey);
        assertThrows(InvalidKeyException.class, () -> quotaFromConfig.getApiKey());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc:fx", "abc:fx ", " abc:fx", " abc:fx "})
    void shouldFormatApiKey(String key) throws InvalidKeyException {
        when(config.getApiKey()).thenReturn(key);
        assertEquals("DeepL-Auth-Key abc:fx", quotaFromConfig.getApiKey());
    }
}
