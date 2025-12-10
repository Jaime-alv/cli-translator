package com.github.jaime.translator.parser.adapter.base;

import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.exception.impl.validation.ValidationException;

public interface ApiKeyAdapter {

    String getApiKey() throws ValidationException;

    static String validateKey(String key) throws InvalidKeyException {
        if (key == null || !key.trim().endsWith(":fx")) {
            throw new InvalidKeyException();
        }
        return String.format("DeepL-Auth-Key %s", key.trim());

    }
}
