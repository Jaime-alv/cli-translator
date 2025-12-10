package com.github.jaime.translator.parser.adapter;

import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;
import com.github.jaime.translator.parser.adapter.base.MessageAdapter;
import com.github.jaime.translator.series.Language;

public interface TranslateAdapter extends ApiKeyAdapter, MessageAdapter {

    @Override
    String getMessage() throws ValidationException ;

    @Override
    String getApiKey() throws ValidationException;

    @Override
    Language getTargetLanguage() throws ValidationException;

    @Override
    Language getFromLanguage() throws ValidationException;
}
