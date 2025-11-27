package com.github.jaime.translator.mapping;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public interface ConfigAdapter {

    Language getTargetLanguage() throws ParserException;

    Language getFromLanguage() throws ParserException;

    APIMode getApiMode() throws ParserException;

    String getAPIKey();

    String getMessage();
}
