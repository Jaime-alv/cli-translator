package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class ParserException extends APIException{

    private final static String message = "Could not parse: %s.";

    public ParserException(String msg) {
        super(ParserException.formatter(msg));
    }

    private static String formatter(String lang) {
        return String.format(message, lang);
    }
}
