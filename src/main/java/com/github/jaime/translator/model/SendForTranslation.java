package com.github.jaime.translator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jaime.translator.series.Language;

public class SendForTranslation {

    String text;

    @JsonProperty("target_lang")
    String targetLang;

    public SendForTranslation() {
    };

    private SendForTranslation(Builder builder) {
        this.text = builder.text;
        this.targetLang = builder.targetLang;
    }

    public static class Builder {
        private String text;
        private String targetLang;
        private String fromLang;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder targetLang(Language lang) {
            this.targetLang = lang.value;
            return this;
        }

        public Builder fromLang(Language lang) {
            this.fromLang = lang.value;
            return this;
        }

        public SendForTranslation build() {
            return new SendForTranslation(this);
        }
    }
}
