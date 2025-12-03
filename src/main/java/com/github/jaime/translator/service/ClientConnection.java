package com.github.jaime.translator.service;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ConnectionException;
import com.github.jaime.translator.exception.impl.MalformedURLException;
import com.github.jaime.translator.model.ClientResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ClientConnection {

    protected final Logger logger = LogManager.getLogger();

    private final HttpClient client = buildClient();

    protected String url;

    protected ClientConnection() {}

    public ClientResponse send() throws ConnectionException {
        try {
            HttpResponse<String> response = sendToClient();
            return new ClientResponse(response.statusCode(), response.body());
        } catch (Exception e) {
            throw new ConnectionException(String.format("Error while connecting to %s", this.url),
                    e);
        }
    }

    protected URI intoUri() throws MalformedURLException {
        try {
            return new URL(this.url).toURI();
        } catch (Exception e) {
            throw new MalformedURLException(url);
        }

    }

    HttpClient buildClient() {
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

    abstract protected HttpRequest buildRequest() throws APIException;
}
