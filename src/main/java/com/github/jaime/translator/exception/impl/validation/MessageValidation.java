package com.github.jaime.translator.exception.impl.validation;

public class MessageValidation extends ValidationException {

    private static final String message = "Message is empty.";

    public MessageValidation() {
        super(message);
    }
}
