package com.github.jaime.translator.parser;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.exception.impl.validation.LanguageValidation;
import com.github.jaime.translator.exception.impl.validation.MessageValidation;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;
import com.github.jaime.translator.series.Language;

public class TranslateFromConfig implements TranslateAdapter {


    private final ConfigAdapter adapter;

    public TranslateFromConfig(ConfigAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public String getMessage() throws ValidationException {
        if (adapter.getTextToTranslate() == null || adapter.getTextToTranslate().trim().isBlank()) {
            throw new MessageValidation();
        }
        return adapter.getTextToTranslate().trim();
    }

    @Override
    public String getApiKey() throws InvalidKeyException {
        String rawValue = adapter.getApiKey();
        return ApiKeyAdapter.validateKey(rawValue);

    }

    @Override
    public Language getTargetLanguage() throws ValidationException {
        try {
            return adapter.getTargetLanguage() == null ? Language.BRITISH
                    : adapter.getTargetLanguage();
        } catch (ParserException e) {
            throw new LanguageValidation();
        }
    }

    @Override
    public Language getFromLanguage() throws ValidationException {
        Language rawLanguage;
        try {
            rawLanguage = adapter.getFromLanguage() == null ? Language.SPANISH
                    : adapter.getFromLanguage();
        } catch (ParserException e) {
            throw new LanguageValidation();
        }
        if (rawLanguage.equals(Language.BRITISH) | rawLanguage.equals(Language.AMERICAN)) {
            return Language.ENGLISH;
        }
        return rawLanguage;
    }

    @Override
    public String getContext() throws ValidationException {
        return adapter.getContext();
    }

}
