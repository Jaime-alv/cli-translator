package com.github.jaime.translator.parser.adapter.base;

import com.github.jaime.translator.series.Language;

public interface MessageAdapter {

    String getMessage();

    Language getTargetLanguage();

    Language getFromLanguage();
}
