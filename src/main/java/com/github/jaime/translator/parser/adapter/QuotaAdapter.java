package com.github.jaime.translator.parser.adapter;

import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.parser.adapter.base.ApiKeyAdapter;

public interface QuotaAdapter extends ApiKeyAdapter {

    @Override
    String getApiKey() throws ValidationException;
}
