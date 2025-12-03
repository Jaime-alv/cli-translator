package com.github.jaime.translator.service.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpRequest;

import org.junit.jupiter.api.Test;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.service.ClientConnection;

public class TestGetQuota {

    private GetClientConnection client = new GetClientConnection("http://random.com", "");

    @Test
    void shouldBeAProperConstructor() {
        assertEquals(GetClientConnection.class, client.getClass());
        assertTrue(client instanceof ClientConnection);
    }


    @Test
    void shouldBuildHttpMethod() throws APIException {
        HttpRequest request = client.buildRequest();
        assertEquals("GET", request.method());
        assertTrue(request instanceof HttpRequest);
    }
}
