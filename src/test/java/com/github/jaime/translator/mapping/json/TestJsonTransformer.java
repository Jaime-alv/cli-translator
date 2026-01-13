package com.github.jaime.translator.mapping.json;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.series.Language;

public class TestJsonTransformer {

    // private static String example =
    // "{\"translations\":[{\"detected_source_language\":\"ES\",\"text\":\"Hello,
    // world!\"}]}";

    @Test
    void shouldReturnProperTranslation() throws JsonException {
        String example = "{\"translations\":[{\"detected_source_language\":\"ES\",\"text\":\"Hello, world!\"}]}";
        Translation translation = new Translation("ES", "Hello, world!");
        Translation[] values = { translation };
        TranslationResponse expected = new TranslationResponse(values);
        assertEquals(expected, JsonTransformer.fromOkResponse(example));
    }

    @Test
    void shouldNotFailWithAddedFields() throws JsonException {
        String example = "{\"translations\":[{\"detected_source_language\":\"ES\",\"text\":\"Hello, world!\",\"model_type_used\":\"quality_optimized\"}]}";
        Translation translation = new Translation("ES", "Hello, world!");
        Translation[] values = { translation };
        TranslationResponse expected = new TranslationResponse(values);
        assertEquals(expected, JsonTransformer.fromOkResponse(example));
    }

    @Test
    void shouldReturnProperErrorMessage() throws JsonException {
        String errorMessage = "{\"message\":\"Forbidden. You can find more info in our docs: ...\"}";
        ErrorMessage expected = new ErrorMessage(
                "Forbidden. You can find more info in our docs: ...");
        assertEquals(expected, JsonTransformer.fromErrorResponse(errorMessage));
    }


    @Test
    void shouldReturnAString() throws JsonException {
        ErrorMessage input = new ErrorMessage("Forbidden. You can find more info in our docs: ...");
        String expected = "{\"message\":\"Forbidden. You can find more info in our docs: ...\"}";
        assertEquals(expected, JsonTransformer.stringify(input));
    }

    @Test
    void shouldReturnAProperDataSender() throws JsonException {
        SendForTranslation data = SendForTranslation.builder().text("DEMO")
                .targetLang(Language.BRITISH).build();
        String expected = "{\"text\":[\"DEMO\"],\"target_lang\":\"EN-GB\"}";
        assertEquals(expected, JsonTransformer.stringify(data));
    }

    @Test
    void shouldNotThrowExceptionForInvalidErrorResponse() {
        String message = "{\"random\": \"BOOM\"}";
        assertDoesNotThrow(() -> JsonTransformer.fromErrorResponse(message));
    }

    @Test
    void shouldReturnAQuota() throws JsonException {
        String response = "{\"character_count\":1400,\"character_limit\":500000}";
        QuotaResponse quota = new QuotaResponse(1400);
        assertEquals(quota, JsonTransformer.fromQuotaResponse(response));
    }
}
