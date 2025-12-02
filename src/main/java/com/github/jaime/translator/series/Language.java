package com.github.jaime.translator.series;

import com.github.jaime.translator.exception.impl.ParserException;

public enum Language {

    ENGLISH("EN"),
    BRITISH("EN-GB"),
    AMERICAN("EN-US"),
    DEUTSCH("DE"),
    FRENCH("FR"),
    SPANISH("ES");

    public final String value;

    private Language(String lang) {
        this.value = lang;
    }

    public static Language parse(String lang) throws ParserException {
        String normalizedLang = lang.toUpperCase().strip();
        switch (normalizedLang) {
            case "GB":
                return Language.BRITISH;
            case "US":
                return Language.AMERICAN;
            case "ES":
                return Language.SPANISH;     
            case "DE":
                return Language.DEUTSCH;
            case "FR":
                return Language.FRENCH;
            case "EN":
                return Language.ENGLISH;
            default:
                throw new ParserException(lang);
        }
    }

}