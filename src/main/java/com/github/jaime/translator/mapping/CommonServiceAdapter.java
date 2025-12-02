package com.github.jaime.translator.mapping;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.series.Language;

public interface CommonServiceAdapter {

    String getMessage();
    String getApiKey() throws InvalidKeyException;
    Language getTargetLanguage();
}
