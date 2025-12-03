package com.github.jaime.translator.parser.adapter;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;
import com.github.jaime.translator.parser.adapter.base.MessageAdapter;
import com.github.jaime.translator.series.Language;

public interface TranslateAdapter extends ApiKeyAdapter, MessageAdapter {

    @Override
    String getMessage();

    @Override
    String getApiKey() throws InvalidKeyException;

    @Override
    Language getTargetLanguage();

    @Override
    Language getFromLanguage();
}
