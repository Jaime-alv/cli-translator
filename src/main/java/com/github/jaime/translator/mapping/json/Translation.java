package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.annotation.JsonProperty;

class Translation {

    @JsonProperty("detected_source_language")
    String fromLanguage;

    @JsonProperty("text")
    String text;

    Translation() {};

    Translation(String fromLanguage, String text) {
        this.text = text;
        this.fromLanguage = fromLanguage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Translation other = (Translation) obj;
        if (fromLanguage == null) {
            if (other.fromLanguage != null)
                return false;
        } else if (!fromLanguage.equals(other.fromLanguage))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
}
