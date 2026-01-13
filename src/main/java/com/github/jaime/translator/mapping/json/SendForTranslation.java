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

    protected SendForTranslation(Builder<?> builder) {
        this.text = builder.text;
        this.targetLang = builder.targetLang;
        this.sourceLang = builder.fromLang;
        this.context = builder.context;
    }

    public static abstract class Builder<T extends Builder<T>> {

        private String[] text;
        private String targetLang;
        private String fromLang;
        private String context;

        protected abstract T self();

        public abstract SendForTranslation build();

        public T text(String text) {
            this.text = intoText(text);
            return self();
        }

        static String[] intoText(String message) {
            String[] a = new String[1];
            a[0] = message;
            return a;

        }

        public T targetLang(Language lang) {
            this.targetLang = lang.value;
            return self();
        }

        public T fromLang(Language lang) {
            this.fromLang = lang.value;
            return self();
        }

        public T context(String context) {
            this.context = context;
            return self();
        }
    }

    public static class SendForTranslationBuilder extends Builder<SendForTranslationBuilder> {
        @Override
        protected SendForTranslationBuilder self() {
            return this;
        }

        @Override
        public SendForTranslation build() {
            return new SendForTranslation(this);
        }
    }

    public static Builder<? extends Builder<?>> builder() {
        return new SendForTranslationBuilder();
    }

    @Override
    public String asJson() throws JsonException {
        return JsonTransformer.stringify(this);
    }
}
