package com.github.jaime.translator.parser.adapter.base;

import com.github.jaime.translator.exception.impl.InvalidKeyException;

public interface ApiKeyAdapter {

    String getApiKey() throws InvalidKeyException;

    static String validateKey(String key) throws InvalidKeyException {
        String rawValue = key.trim();
        if (rawValue.endsWith(":fx")) {
            return String.format("DeepL-Auth-Key %s", rawValue);
        }
        throw new InvalidKeyException();
    }
}
