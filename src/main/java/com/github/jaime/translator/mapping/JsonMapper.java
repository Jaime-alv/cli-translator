package com.github.jaime.translator.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaime.translator.exception.impl.JsonException;
import com.github.jaime.translator.model.Translation;

public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String stringify(Object object) throws JsonException {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(String.format("Fail processing class %s", object.getClass()), e);
        }

    }

    public static Translation fromString(String value) throws JsonException {
        try {
            return mapper.readValue(value, Translation.class);
        } catch (JsonProcessingException e) {
            throw new JsonException(String.format("Fail processing string %s", value), e);
        }
    }
}
