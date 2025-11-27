package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class ConfigException extends APIException {

    private final static String message = "Build object first.";

    public ConfigException() {
        super(message);
    }

}
