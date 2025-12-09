package com.github.jaime.translator.parser;

import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.parser.adapter.QuotaAdapter;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;

public class QuotaFromConfig implements QuotaAdapter {

    private final Config config;

    public QuotaFromConfig(Config config) {
        this.config = config;
    }

    @Override
    public String getApiKey() throws InvalidKeyException {
        String rawValue = config.getApiKey();
        return ApiKeyAdapter.validateKey(rawValue);
    }

}
