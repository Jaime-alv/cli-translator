package com.github.jaime.translator.mapping.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.series.Language;

public class TestSendForTranslation {

    @ParameterizedTest
    @ValueSource(strings = { "DEMO", "", "  ", "demo" })
    void shouldBuildObject(String value) {
        SendForTranslation data = SendForTranslation.builder().text(value).build();
        assertEquals(value, Stream.of(data.text).findFirst().get());
    }

    @Test
    void shouldReturnAProperDataSender() throws JsonException {
        SendForTranslation data = SendForTranslation.builder().text("DEMO")
                .targetLang(Language.BRITISH).fromLang(Language.SPANISH).context("this is context")
                .build();
        String expected = "{\"text\":[\"DEMO\"],\"target_lang\":\"EN-GB\",\"source_lang\":\"ES\",\"context\":\"this is context\"}";
        assertEquals(expected, data.asJson());
    }

    @Test
    void shouldNotReturnNullValueForFromLangOrContext() throws JsonException {
        SendForTranslation data = SendForTranslation.builder().text("DEMO")
                .targetLang(Language.BRITISH).build();
        String expected = "{\"text\":[\"DEMO\"],\"target_lang\":\"EN-GB\"}";
        assumeTrue(data.context == null);
        assumeTrue(data.sourceLang == null);
        assertEquals(expected, data.asJson());
    }

    @Test
    void shouldCastToArray() {
        String value = "example";
        String[] textValue = SendForTranslation.Builder.intoText(value);
        assertTrue(textValue.length == 1);
        assertTrue(textValue[0] == "example");
    }

    @Test
    void s() {
        SendForTranslation a = new SendForTranslation.SendForTranslationBuilder().build();
    }
}
