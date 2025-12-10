package com.github.jaime.translator.parser.adapter.base;

import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.series.Language;

public interface MessageAdapter {

    String getMessage() throws ValidationException ;

    Language getTargetLanguage() throws ValidationException;

    Language getFromLanguage() throws ValidationException;
}
