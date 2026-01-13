package com.github.jaime.translator.mapping.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.series.Language;

public class TestBetaTranslation {

    @Test
    void shouldAddNewFields() throws JsonException {
        SendForTranslationBeta beta = SendForTranslationBeta.builder()
                .fromLang(Language.ENGLISH).targetLang(Language.SPANISH).text("Hello world")
                .build();
        assertEquals(
                "{\"text\":[\"Hello world\"],\"target_lang\":\"ES\",\"source_lang\":\"EN\",\"enable_beta_languages\":true,\"model_type\":\"quality_optimized\"}",
                beta.asJson());
    }

    @Test
    void shouldBeBetaTranslationClass() {
        SendForTranslationBeta beta = SendForTranslationBeta.builder()
                .fromLang(Language.ENGLISH).targetLang(Language.SPANISH).text("Hello world")
                .build();
        assertEquals(SendForTranslationBeta.class, beta.getClass());
    }

    @Test
    void shouldBeBuiltDirectly() {
                SendForTranslationBeta beta = new SendForTranslationBeta.BetaBuilder()
                .build();
        assertEquals(SendForTranslationBeta.class, beta.getClass());
    }
}
