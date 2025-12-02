package com.github.jaime.translator.service;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.mapping.ResponseInterface;

public interface TranslationService {

    ResponseInterface getResponse() throws APIException;

}
