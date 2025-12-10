package com.github.jaime.translator.parser;

import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.parser.adapter.QuotaAdapter;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;

public class QuotaFromConfig implements QuotaAdapter {

    private final ConfigAdapter adapter;

    public QuotaFromConfig(ConfigAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public String getApiKey() throws InvalidKeyException {
        String rawValue = adapter.getApiKey();
        return ApiKeyAdapter.validateKey(rawValue);
    }

}
