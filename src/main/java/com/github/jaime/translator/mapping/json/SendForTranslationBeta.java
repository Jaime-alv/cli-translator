package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendForTranslationBeta extends SendForTranslation {

    @JsonProperty("enable_beta_languages")
    final Boolean enableBetaLanguages = true;

    @JsonProperty("model_type")
    final String modelType = "quality_optimized";

    private SendForTranslationBeta(Builder<?> builder) {
        super(builder);
    }

    public static class BetaBuilder extends SendForTranslation.Builder<BetaBuilder> {
        @Override
        protected BetaBuilder self() {
            return this;
        }

        @Override
        public SendForTranslationBeta build() {
            return new SendForTranslationBeta(this);
        }
    }

    public static BetaBuilder builder() {
        return new BetaBuilder();
    }
}
