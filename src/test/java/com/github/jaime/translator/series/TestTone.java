package com.github.jaime.translator.series;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestTone {

    @Test
    void shouldReturnTheStringValue() {
        assertEquals("formal", Tone.FORMAL.value);
    }
}
