package com.github.jaime.translator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.mapping.ResponseInterface;
import com.github.jaime.translator.mapping.json.JsonTransformer;
import com.github.jaime.translator.model.ClientResponse;
import com.github.jaime.translator.model.SendForTranslation;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.series.Language;
import com.github.jaime.translator.service.ClientConnection;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.http.PostClientConnection;

public class TranslateFromDeepL extends TranslationService {

    private final Logger logger = LogManager.getLogger();

    private static String URL = "https://api-free.deepl.com/v2/translate";

    private final String apiKey;
    private final Language fromLanguage;
    private final Language targetLanguage;
    private final String textToTranslate;

    public TranslateFromDeepL(TranslateAdapter adapter)
            throws ValidationException {
        this.apiKey = adapter.getApiKey();
        this.fromLanguage = adapter.getFromLanguage();
        this.targetLanguage = adapter.getTargetLanguage();
        this.textToTranslate = adapter.getMessage();

    }

    SendForTranslation buildBody() {
        return new SendForTranslation.Builder().text(textToTranslate).targetLang(targetLanguage)
                .fromLang(fromLanguage).build();
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
        ClientConnection service = new PostClientConnection(URL, body, apiKey);
        logger.debug("Create new connection service.");
        return service;
    }
}
