package com.github.jaime.translator.exception.impl;

import com.github.jaime.translator.exception.APIException;

public class InvalidKeyException extends APIException{

    private static String message = "Key should end with :fx";

    public InvalidKeyException() {
        super(message);
    }

}
