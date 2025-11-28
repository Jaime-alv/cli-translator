package com.github.jaime.translator.service;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ConnectionException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.MalformedURLException;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.model.SendData;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientConnection {

    private static final Logger logger = LogManager.getLogger();
    private final HttpClient client = buildClient();

    private final String url;
    private final SendData body;
    private final String apiKey;

    public ClientConnection(String url, SendData body, String apiKey) throws APIException {
        this.url = url;
        this.body = body;
        this.apiKey = apiKey;
    }

    public ClientResponse send() throws ConnectionException {
        try {
            HttpResponse<String> response = sendToClient();
            return new ClientResponse(response.statusCode(), response.body());
        } catch (Exception e) {
            throw new ConnectionException(String.format("Error while connecting to %s", this.url),
                    e);
        }
    }

    final URI intoUri() throws MalformedURLException {
        try {
            return new URL(this.url).toURI();
        } catch (Exception e) {
            throw new MalformedURLException(url);
        }

    }

    static HttpClient buildClient() {
        return HttpClient.newBuilder().followRedirects(Redirect.ALWAYS).build();
    }

    protected HttpResponse<String> sendToClient() throws APIException {
        try {
            HttpRequest request = buildRequest();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            logger.error(e);
            throw new ConnectionException("Error while sending the request", e);

        }
    }

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
