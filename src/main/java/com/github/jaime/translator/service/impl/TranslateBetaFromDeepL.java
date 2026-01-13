package com.github.jaime.translator.service.impl;

import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.mapping.json.SendForTranslation;
import com.github.jaime.translator.mapping.json.SendForTranslationBeta;
import com.github.jaime.translator.parser.adapter.TranslateAdapter;

public class TranslateBetaFromDeepL extends TranslateFromDeepL {

    public TranslateBetaFromDeepL(TranslateAdapter adapter) {
        super(adapter);
    }

    @Override
    SendForTranslation buildBody() throws ValidationException {
        return SendForTranslationBeta.builder().text(adapter.getMessage())
                .fromLang(adapter.getFromLanguage()).targetLang(adapter.getTargetLanguage())
                .context(adapter.getContext()).build();
    }
}
