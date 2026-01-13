package com.github.jaime.translator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.mapping.ResponseInterface;
import com.github.jaime.translator.mapping.json.JsonTransformer;
import com.github.jaime.translator.mapping.json.SendForTranslation;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.service.ClientConnection;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.http.PostClientConnection;

public class TranslateFromDeepL extends TranslationService {

    private final Logger logger = LogManager.getLogger();

    private static String URL = "https://api-free.deepl.com/v2/translate";

    protected final TranslateAdapter adapter;

    public TranslateFromDeepL(TranslateAdapter adapter) {
        this.adapter = adapter;
    }

    SendForTranslation buildBody() throws ValidationException {
        return SendForTranslation.builder().text(adapter.getMessage())
                .fromLang(adapter.getFromLanguage()).targetLang(adapter.getTargetLanguage())
                .context(adapter.getContext()).build();
    }

    @Override
    protected ResponseInterface returnResponseBody(ClientResponse response) throws JsonException {
        logger.debug("Parse response from client.");
        switch (response.statusCode) {
        case 200:
            return JsonTransformer.fromOkResponse(response.body);
        default:
            logger.warn("Response code {}!", response.statusCode);
            return parseErrorMessage(response.body);
        }
    }

    @Override
    protected ClientConnection prepareRequest() throws APIException {
        SendForTranslation body = buildBody();
        ClientConnection service = new PostClientConnection(URL, body, adapter.getApiKey());
        logger.debug("Create new connection service.");
        return service;
    }
}
