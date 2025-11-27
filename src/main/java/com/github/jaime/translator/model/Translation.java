package com.github.jaime.translator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Translation {

    String example = "{\"translations\":[{\"detected_source_language\":\"ES\",\"text\":\"Develop a multi-purpose endpoint where you can resolve an IP address.\"}]}";

    @JsonProperty("translations")
    String translations;

    public Translation() {
    }

}
