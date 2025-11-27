package com.github.jaime.translator.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaime.translator.model.Translation;

public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String stringify(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static Translation fromString(String value) throws JsonMappingException, JsonProcessingException {
        return mapper.readValue(value, Translation.class);
    }
}
