package com.github.jaime.translator.series;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.impl.ParserException;

public enum APIMode {
    TRANSLATE,
    SPELLING;

    private static Logger logger = LogManager.getLogger();

    public static APIMode parse(String value) throws ParserException {
        String normalisedString = value.toUpperCase().strip();
        try {
            return APIMode.valueOf(normalisedString);
        } catch (IllegalArgumentException e) {
            logger.error("Could not parse String {}", value);
            throw new ParserException(value);
        }
    }
}
