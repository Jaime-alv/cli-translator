package com.github.jaime.translator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.MalformedURLException;
import com.github.jaime.translator.model.ClientResponse;

public class TestClientConnection {

    private static class DemoConnection extends ClientConnection {

        @Override
        protected HttpRequest buildRequest() throws APIException {
            return HttpRequest.newBuilder(intoUri()).GET().build();
        }
    }

    private static HttpResponse<String> response;
    private static DemoConnection client;

    @SuppressWarnings("unchecked")
    @BeforeAll
    static void setUp() throws APIException {
        response = (HttpResponse<String>) mock(HttpResponse.class);
        client = spy(new DemoConnection());
        client.url = "https://www.random.com";

    }

    @ParameterizedTest
    @ValueSource(ints = { 200, 404, 500 })
    void shouldReturnAResponse(int code) throws APIException {
        when(response.statusCode()).thenReturn(code);
        when(client.sendToClient()).thenReturn(response);
        assertEquals(code, client.send().statusCode);
        assertEquals(ClientResponse.class, client.send().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = { "httpsapi-random.com", "www.unknown.es" })
    void allInvalidUrlsShouldFail(String url) throws APIException {
        client.url = url;
        assertThrows(MalformedURLException.class, () -> client.intoUri());
    }
}
