package com.github.jaime.translator.service.http;

import java.net.http.HttpRequest;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.service.ClientConnection;

public class GetClientConnection extends ClientConnection {

    private final String apiKey;

    public GetClientConnection(String url, String apiKey) {
        super.url = url;
        this.apiKey = apiKey;
    }

    @Override
    protected HttpRequest buildRequest() throws APIException {
        return HttpRequest.newBuilder(intoUri()).header("Content-Type", "application/json")
                .header("Authorization", this.apiKey).GET().build();
    }

}
