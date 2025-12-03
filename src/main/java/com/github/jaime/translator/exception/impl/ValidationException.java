package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class ValidationException extends APIException{

    private static final String message = "Message is empty.";

    public ValidationException() {
        super(message);
    }

}
