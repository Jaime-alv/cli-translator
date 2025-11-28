package com.github.jaime.translator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.net.http.HttpResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.MalformedURLException;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.model.SendForTranslation;
import com.github.jaime.translator.series.Language;

public class TestClientConnection {

    private static final Logger logger = LogManager.getLogger();

    private static String URL = "https://api-free.deepl.com/v2/translate";
    private static SendForTranslation example = new SendForTranslation.Builder("Hello, world!", Language.SPANISH).build();
    private static String APIKey = "DeepL-Auth-Key :fx";

    private static ClientConnection client;

    private static HttpResponse<String> response;

    @SuppressWarnings("unchecked")
    @BeforeAll
    static void setUp() throws APIException {
        client = spy(new ClientConnection(URL, example, APIKey));
        response = (HttpResponse<String>) mock(HttpResponse.class);

    }

    @ParameterizedTest
    @ValueSource(ints = {200, 404, 500})
    void shouldReturnAResponse(int code) throws APIException {
        when(response.statusCode()).thenReturn(code);
        when(client.sendToClient()).thenReturn(response);
        assertEquals(code, client.send().statusCode);
        assertEquals(ClientResponse.class, client.send().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"httpsapi-random.com", "www.unknown.es"})
    void allInvalidUrlsShouldFail(String url) throws APIException {
        ClientConnection client = new ClientConnection(url, example, APIKey);
        assertThrows(MalformedURLException.class, () -> client.intoUri());
    }

}
