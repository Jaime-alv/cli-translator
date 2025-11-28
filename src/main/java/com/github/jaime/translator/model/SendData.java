package com.github.jaime.translator.model;

import com.github.jaime.translator.exception.impl.JsonException;

public interface SendData {

    public String asJson() throws JsonException;
}
