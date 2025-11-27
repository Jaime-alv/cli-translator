package com.github.jaime.translator.service;

import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jaime.translator.configuration.Config;
import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ConnectionException;
import com.github.jaime.translator.exception.impl.MalformedURLException;
import com.github.jaime.translator.mapping.JsonMapper;
import com.github.jaime.translator.model.SendForTranslation;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientConnection {

    private static final Logger logger = LogManager.getLogger();

    private URI uri;
    private final String url;
    private final SendForTranslation body;
    private Config config = Config.getInstance();

    /**
     * Respuesta completa en formato String, así evitamos llamar varias veces para
     * comprobar el estado de la url
     */
    private HttpResponse<String> response;
    private final HttpClient client;

    public ClientConnection(String url, SendForTranslation body) throws APIException {
        this.url = url;
        this.client = buildClient();
        this.body = body;
    }

    public ClientConnection connect() throws ConnectionException {
        try {
            this.response = this.buildHttpResponse();
        } catch (Exception e) {
            throw new ConnectionException(String.format("Error while connecting to %s", this.uri.toString()), e);
        }
        return this;

    }

    public String getURL() {
        return this.url;
    }

    static final URI intoUri(String url) throws MalformedURLException {
        try {
            return new URL(url).toURI();
        } catch (Exception e) {
            throw new MalformedURLException(url);
        }

    }

    protected HttpResponse<String> buildHttpResponse() throws APIException {
        this.uri = ClientConnection.intoUri(this.url);
        logger.debug("Reach out <{}>", this.url);
        BodyPublisher body = BodyPublishers.ofString("");
        HttpRequest request = HttpRequest.newBuilder(this.uri).header("Content-Type", "application/json")
                .header("Authorization", authKey()).POST(body).build();
        HttpResponse<String> tmpResponse = null;
        try {
            tmpResponse = this.sendToClient(request);
            logger.debug("Response code {}", tmpResponse.statusCode());
            return tmpResponse;
        } catch (Exception e) {
            logger.error(e);
            throw new ConnectionException(String.format("Error while building HttpResponse to %s", this.url), e);
        }

    }

    static HttpClient buildClient() {
        return HttpClient.newBuilder().followRedirects(Redirect.ALWAYS).build();
    }

    protected HttpResponse<String> sendToClient(HttpRequest request) throws Exception, ConnectException {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    protected String authKey() {
        return String.format("DeepL-Auth-Key %s", this.config.apiKey);
    }

    class InnerTest {

    }

    protected BodyPublisher bodyPublisher() throws JsonProcessingException {
        return BodyPublishers.ofString(JsonMapper.stringify(body));
    }

    public Integer getResponseCode() {
        return response.statusCode();
    }

    public String getResponseBody() {
        return response.body();

    }

    public HttpResponse<String> getResponse() {
        return this.response;
    }
}
