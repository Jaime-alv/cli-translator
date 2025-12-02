package com.github.jaime.translator.mapping;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.series.Language;

public interface TranslateAdapter extends CommonServiceAdapter {

    String getMessage();

    @Override
    String getApiKey() throws InvalidKeyException;

    Language getTargetLanguage();

    Language getFromLanguage();
}
