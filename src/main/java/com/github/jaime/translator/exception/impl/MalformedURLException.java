package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class MalformedURLException extends APIException{

    private static final String MSG = "Not a valid URL %s";

    public MalformedURLException(String msg) {
        super(MalformedURLException.builder(msg));
    }

    static String builder(String reason) {
        return String.format(MSG, reason);
    }
    
}