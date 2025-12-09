package com.github.jaime.translator.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.ValidationException;
import com.github.jaime.translator.mapping.json.ErrorMessage;
import com.github.jaime.translator.mapping.json.TranslationResponse;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.model.SendForTranslation;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.series.Language;
import com.github.jaime.translator.service.http.PostClientConnection;

public class TestTranslateFromDeepL {

    private static class InnerConstructor implements TranslateAdapter {

        @Override
        public String getMessage() throws ValidationException {
            return "Hello world";
        }

        @Override
        public String getApiKey() throws InvalidKeyException {
            return "DeepL-Auth-Key :fx";
        }

        @Override
        public Language getTargetLanguage() {
            return Language.DEUTSCH;
        }

        @Override
        public Language getFromLanguage() {
            return Language.ENGLISH;
        }
    }

    private static TranslateFromDeepL translator;

    @BeforeAll
    static void setUp() throws InvalidKeyException, ValidationException {
        translator = spy(new TranslateFromDeepL(new InnerConstructor()));
    }

    @ParameterizedTest
    @ValueSource(ints = {201, 204, 400, 500})
    void shouldReturnErrorResponse(int statusCode) throws JsonException {
        ClientResponse response = new ClientResponse(statusCode, "{\"message\": \"Forbidden\"}");
        ErrorMessage expected = new ErrorMessage("Forbidden");
        assertEquals(expected, translator.returnResponseBody(response));
    }

    @Test
    void shouldReturnOkResponse() throws JsonException {
        String example = "{\"translations\":[{\"detected_source_language\":\"ES\",\"text\":\"Hello, world!\"}]}";
        ClientResponse response = new ClientResponse(200, example);
        assertEquals(TranslationResponse.class, translator.returnResponseBody(response).getClass());
        assertEquals("Hello, world!", translator.returnResponseBody(response).getText());
    }

    @Test
    void shouldCreateBody() throws JsonException {
        assertEquals(SendForTranslation.class, translator.buildBody().getClass());
        assertEquals("{\"text\":[\"Hello world\"],\"target_lang\":\"DE\",\"source_lang\":\"EN\"}", translator.buildBody().asJson());
    }

    @Test
    void shouldCreatePostClient() throws APIException {
        assertTrue(translator.prepareRequest() instanceof PostClientConnection);
    }
}
