package com.github.jaime.translator.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.mapping.json.ErrorMessage;
import com.github.jaime.translator.mapping.json.QuotaResponse;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.parser.adapter.QuotaAdapter;
import com.github.jaime.translator.service.http.GetClientConnection;

public class TestQuotaFromDeepL {

    private static class InnerClass implements QuotaAdapter {

        @Override
        public String getApiKey() throws InvalidKeyException {
            return "DeepL Auth key :fx";
        }

    }

    private static QuotaFromDeepL quota;

    @BeforeAll
    static void setUp() throws InvalidKeyException {
        quota = new QuotaFromDeepL(new InnerClass());
    }

    @ParameterizedTest
    @ValueSource(ints = { 201, 400, 500 })
    void shouldReturnErrorResponse(int statusCode) throws JsonException {
        ClientResponse response = new ClientResponse(statusCode, "{\"message\": \"error\"}");
        ErrorMessage expected = new ErrorMessage("error");
        assertEquals(expected, quota.returnResponseBody(response));
    }

    @Test
    void shouldReturnQuotaResponseIf200() throws JsonException {
        ClientResponse response = new ClientResponse(200,
                "{\"character_count\":140,\"character_limit\":500}");
        QuotaResponse expected = new QuotaResponse(140, 500);
        assertEquals(expected, quota.returnResponseBody(response));
    }

    @Test
    void shouldCreateGetClientConnection() throws APIException {
        assertTrue(quota.prepareRequest() instanceof GetClientConnection);
    }
}
