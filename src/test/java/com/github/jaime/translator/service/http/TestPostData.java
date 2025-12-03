package com.github.jaime.translator.service.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpRequest;

import org.junit.jupiter.api.Test;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.model.SendData;
import com.github.jaime.translator.service.ClientConnection;

public class TestPostData {

    private class InnerSend implements SendData {

        @Override
        public String asJson() throws JsonException {
            return "{\"message\": \"Success\"}";
        }

    }

    private PostClientConnection client = new PostClientConnection("http://random.com",
            new InnerSend(), "key");

    @Test
    void shouldBeAProperConstructor() {
        assertEquals(PostClientConnection.class, client.getClass());
        assertTrue(client instanceof ClientConnection);
    }

    @Test
    void shouldBuildHttpMethod() throws APIException {
        HttpRequest request = client.buildRequest();
        assertEquals("POST", request.method());
        assertTrue(request instanceof HttpRequest);
    }
}
