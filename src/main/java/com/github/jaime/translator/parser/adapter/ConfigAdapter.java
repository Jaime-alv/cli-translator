package com.github.jaime.translator.parser.adapter;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public interface ConfigAdapter {

    Language getTargetLanguage() throws ParserException;

    Language getFromLanguage() throws ParserException;

    APIMode getApiMode() throws ParserException;

    String getApiKey();

    String getTextToTranslate();

    String getContext();

    static Language intoLanguage(String lang) throws ParserException {
        return lang == null ? null : Language.parse(lang);
    }
}
