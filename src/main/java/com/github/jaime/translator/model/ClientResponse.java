package com.github.jaime.translator.model;

public class ClientResponse {

    public final int statusCode;
    public final String body;

    public ClientResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
