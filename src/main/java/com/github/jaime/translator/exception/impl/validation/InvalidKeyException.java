package com.github.jaime.translator.exception.impl.validation;


public class InvalidKeyException extends ValidationException {

    private static final String message = "Key should end with :fx";

    public InvalidKeyException() {
        super(message);
    }

}
