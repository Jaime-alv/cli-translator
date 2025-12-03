package com.github.jaime.translator.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.ValidationException;
import com.github.jaime.translator.mapping.json.ErrorMessage;
import com.github.jaime.translator.mapping.json.TranslationResponse;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.model.SendForTranslation;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.series.Language;

public class TestTranslateFromDeepL {

    private static class InnerConstructor implements TranslateAdapter {

        @Override
        public String getMessage() throws ValidationException {
            return "Hello world";
        }

        @Override
        public String getApiKey() throws InvalidKeyException {
            return "API-KEY";
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

    @Test
    void shouldReturnErrorResponse() throws JsonException {
        ClientResponse response = new ClientResponse(400, "{\"message\": \"Forbidden\"}");
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
        assertEquals("{\"text\":[\"Hello world\"],\"target_lang\":\"DE\"}", translator.buildBody().asJson());
    }

}
