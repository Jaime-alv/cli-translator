package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.jaime.translator.exception.impl.JsonException;

public class JsonTransformer {

    static final ObjectMapper mapper = new ObjectMapper();

    static final JsonMapper jsonMapper = JsonMapper.builder()
            .disable(MapperFeature.AUTO_DETECT_GETTERS).build();

    public static String stringify(Object object) throws JsonException {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(String.format("Fail processing class %s", object.getClass()),
                    e);
        }

    }

    public static TranslationResponse fromOkResponse(String value) throws JsonException {
        try {
            return mapper.readValue(value, TranslationResponse.class);
        } catch (JsonProcessingException e) {
            throw new JsonException(String.format("Fail processing string %s", value), e);
        }
    }

    public static ErrorMessage fromErrorResponse(String value) throws JsonException {
        try {
            return mapper.readValue(value, ErrorMessage.class);
        } catch (Exception e) {
            throw new JsonException(String.format("Fail processing string %s", value), e);
        }

    }

}
