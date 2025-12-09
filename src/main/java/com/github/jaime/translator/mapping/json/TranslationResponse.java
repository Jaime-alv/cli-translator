package com.github.jaime.translator.mapping.json;

import java.util.Arrays;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jaime.translator.mapping.ResponseInterface;

public class TranslationResponse implements ResponseInterface{

    @JsonProperty("translations")
    Translation[] translations;

    TranslationResponse() {}

    TranslationResponse(Translation[] translations) {
        this.translations = translations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TranslationResponse other = (TranslationResponse) obj;
        if (!Arrays.equals(translations, other.translations))
            return false;
        return true;
    }

    @Override
    public String getText() {
        return Stream.of(translations).findFirst().get().text;
    }
}
