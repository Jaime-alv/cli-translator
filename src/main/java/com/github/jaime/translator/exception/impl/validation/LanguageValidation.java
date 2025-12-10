package com.github.jaime.translator.exception.impl.validation;

public class LanguageValidation extends ValidationException {
    private static final String message = "Language is not valid.";

    public LanguageValidation() {
        super(message);
    }
}
