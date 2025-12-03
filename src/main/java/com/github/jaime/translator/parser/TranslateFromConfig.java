package com.github.jaime.translator.parser;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;
import com.github.jaime.translator.series.Language;

public class TranslateFromConfig implements TranslateAdapter {
    private final Config config;

    public TranslateFromConfig(Config config) {
        this.config = config;
    }

    @Override
    public String getMessage() {
        return config.getTextToTranslate();
    }

    @Override
    public String getApiKey() throws InvalidKeyException {
        String rawValue = config.getApiKey();
        if (rawValue.endsWith(":fx")) {
            return String.format("DeepL-Auth-Key %s", rawValue);
        }
        throw new InvalidKeyException();
        
    }

    @Override
    public Language getTargetLanguage() {
        return config.getTarget().orElse(Language.BRITISH);
    }

    @Override
    public Language getFromLanguage() {
        Language rawLanguage = config.getFrom().orElse(Language.SPANISH);
        if (rawLanguage.equals(Language.BRITISH) | rawLanguage.equals(Language.AMERICAN)) {
            return Language.ENGLISH;
        }
        return rawLanguage;
    }

}
