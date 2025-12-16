package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.model.SendData;
import com.github.jaime.translator.series.Language;

public class SendForTranslation implements SendData {

    @JsonProperty(value = "text")
    String[] text;

    @JsonProperty(value = "target_lang")
    String targetLang;

    @JsonProperty("source_lang")
    @JsonInclude(value = Include.NON_NULL)
    String sourceLang;

    @JsonProperty("context")
    @JsonInclude(value = Include.NON_NULL)
    String context;

    public SendForTranslation() {};

    private SendForTranslation(Builder builder) {
        this.text = builder.text;
        this.targetLang = builder.targetLang;
        this.sourceLang = builder.fromLang;
        this.context = builder.context;
    }

    public static class Builder {
        private String[] text;
        private String targetLang;
        private String fromLang;
        private String context;

        public Builder() {};

        public Builder text(String text) {
            this.text = intoText(text);
            return this;
        }

        static String[] intoText(String message) {
            String[] a = new String[1];
            a[0] = message;
            return a;

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

        public Builder context(String context) {
            this.context = context;
            return this;
        }
    }

    @Override
    public String asJson() throws JsonException {
        return JsonTransformer.stringify(this);
    }
}
