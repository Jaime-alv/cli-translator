package com.github.jaime.translator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.mapping.ResponseInterface;
import com.github.jaime.translator.mapping.json.JsonTransformer;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.parser.adapter.QuotaAdapter;
import com.github.jaime.translator.service.ClientConnection;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.http.GetClientConnection;

public class QuotaFromDeepL extends TranslationService {

    private final Logger logger = LogManager.getLogger();

    private final static String URL = "https://api-free.deepl.com/v2/usage";

    private final String apiKey;

    public QuotaFromDeepL(QuotaAdapter adapter) throws ValidationException {
        this.apiKey = adapter.getApiKey();
    }

    @Override
    protected ResponseInterface returnResponseBody(ClientResponse response) throws JsonException {
        logger.debug("Parse response from client.");
        switch (response.statusCode) {
        case 200:
            return JsonTransformer.fromQuotaResponse(response.body);
        default:
            logger.warn("Response code {}!", response.statusCode);
            return parseErrorMessage(response.body);
        }
    }

    @Override
    protected ClientConnection prepareRequest() throws APIException {
        ClientConnection service = new GetClientConnection(URL, this.apiKey);
        return service;
    }

}
