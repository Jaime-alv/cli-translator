package com.github.jaime.translator.exception.impl.validation;

import com.github.jaime.translator.exception.APIException;

public abstract class ValidationException extends APIException{

    ValidationException(String message) {
        super(message);
    }

}
