package com.github.jaime.translator.parser;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.mapping.ConfigAdapter;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public class Config {

    private static Config instance;

    private final APIMode mode;
    private final Language target;
    private final Language from;
    private final String apiKey;
    private final String textToTranslate;


    Config(ConfigAdapter adapter) throws ParserException {
        this.mode = adapter.getApiMode();
        this.target = adapter.getTargetLanguage();
        this.from = adapter.getFromLanguage();
        this.apiKey = adapter.getAPIKey();
        this.textToTranslate = adapter.getMessage();
    }

    private Config(Builder builder) {
        this.apiKey = builder.apiKey;
        this.from = builder.from;
        this.target = builder.target;
        this.textToTranslate = builder.textToTranslate;
        this.mode = builder.mode;
    }

    static class Builder {
        private APIMode mode;
        private Language target;
        private Language from;
        private String apiKey;
        private String textToTranslate;

        Builder mode(APIMode mode) {
            this.mode = mode;
            return this;
        }

        Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        Builder targetLang(Language target) {
            this.target = target;
            return this;
        }

        Builder fromLang(Language from) {
            this.from = from;
            return this;
        }

        Builder text(String text) {
            this.textToTranslate = text;
            return this;
        }

        Config build() {
            return new Config(this);
        }

    }

    public static Config getInstance(ConfigAdapter adapter) throws ParserException {
        if (instance == null) {
            instance = new Config(adapter);
        }
        return instance;
    }

    public APIMode getMode() {
        return mode;
    }

    public Optional<Language> getTarget() {
        return Optional.ofNullable(target);
    }

    public Optional<Language> getFrom() {
        return Optional.ofNullable(from);
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }
}
