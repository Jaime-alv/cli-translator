package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class JsonException extends APIException{

    public JsonException(String msg, Throwable err) {
        super(msg, err);
    }

}
