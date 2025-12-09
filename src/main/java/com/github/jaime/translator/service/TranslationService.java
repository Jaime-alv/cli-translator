package com.github.jaime.translator.service;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.mapping.ResponseInterface;
import com.github.jaime.translator.mapping.json.ErrorMessage;
import com.github.jaime.translator.mapping.json.JsonTransformer;
import com.github.jaime.translator.model.ClientResponse;

public abstract class TranslationService {

    protected abstract ClientConnection prepareRequest() throws APIException;

    protected abstract ResponseInterface returnResponseBody(ClientResponse response) throws JsonException;

    public ResponseInterface getResponse() throws APIException {
        ClientResponse response = prepareRequest().send();
        return returnResponseBody(response);
    }

    protected ErrorMessage parseErrorMessage(String incomingResponse) throws JsonException {
        return JsonTransformer.fromErrorResponse(incomingResponse);
    }

}
