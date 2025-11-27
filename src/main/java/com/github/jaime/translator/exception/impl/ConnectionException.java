package com.github.jaime.translator.exception.impl;

import java.net.http.HttpResponse;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.service.ClientConnection;

public class ConnectionException extends APIException {

    private static final String MSG = "Encounter a problem while connecting to %s...\n>>> Response code: %d\n>>> Response body:\n%s";

    Integer code;
    String body;

    public ConnectionException(ClientConnection client) {
        super(ConnectionException.msgBuilder(client));
        this.body = client.getResponseBody();
        this.code = client.getResponseCode();
    }

    public ConnectionException(HttpResponse<String> response) {
        super(ConnectionException.msgBuilder(response.statusCode(), response.body()));
        this.code = response.statusCode();
        this.body = response.body();
    }

    public ConnectionException(String msg, Throwable e) {
        super(msg, e);
    }

    static String msgBuilder(ClientConnection client) {
        Integer code = client.getResponseCode();
        String body = client.getResponseBody();
        return String.format(MSG, client.getURL(), code, body);
    }

    static String msgBuilder(Integer statusCode, String msg) {
        return String.format(MSG, "", statusCode, msg);
    }

}
