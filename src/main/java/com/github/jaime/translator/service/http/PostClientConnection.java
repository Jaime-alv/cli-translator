package com.github.jaime.translator.service.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.model.SendData;
import com.github.jaime.translator.service.ClientConnection;

public class PostClientConnection extends ClientConnection {

    private final SendData body;
    private final String apiKey;

    public PostClientConnection(String url, SendData body, String apiKey) {
        this.body = body;
        this.apiKey = apiKey;
        super.url = url;
    }

    @Override
    protected HttpRequest buildRequest() throws APIException {
        URI uri = intoUri();
        BodyPublisher body = bodyPublisher();
        HttpRequest request = HttpRequest.newBuilder(uri).header("Content-Type", "application/json")
                .header("Authorization", this.apiKey).POST(body).build();
        return request;
    }

    protected BodyPublisher bodyPublisher() throws JsonException {
        String value = body.asJson();
        logger.debug(value);
        return BodyPublishers.ofString(value);
    }
}
