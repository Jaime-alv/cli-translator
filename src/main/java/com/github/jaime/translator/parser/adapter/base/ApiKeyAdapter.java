package com.github.jaime.translator.parser.adapter.base;

import com.github.jaime.translator.exception.impl.InvalidKeyException;

public interface ApiKeyAdapter {

    String getApiKey() throws InvalidKeyException;
}
