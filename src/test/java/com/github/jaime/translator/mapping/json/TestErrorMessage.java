package com.github.jaime.translator.mapping.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestErrorMessage {

    @Test
    void shouldBeEquals() {
        ErrorMessage error = new ErrorMessage("random");
        ErrorMessage expected = new ErrorMessage("random");
        assertTrue(error.equals(expected));
    }

    @Test
    void shouldNotBeEquals() {
        ErrorMessage error = new ErrorMessage("random");
        ErrorMessage expected = new ErrorMessage("other");
        assertFalse(error.equals(expected));
    }

    @Test
    void shouldNotBeEqualsNull() {
        ErrorMessage error = new ErrorMessage("random");
        assertFalse(error.equals(null));
    }

    @Test
    void shouldReturnBodyMessage() {
        ErrorMessage error = new ErrorMessage("error message");
        assertEquals("error message", error.getText());
    }

    @Test
    void shouldMaintainMessage() {
        ErrorMessage error = new ErrorMessage("forbidden");
        assertEquals(error.message, error.getText());
    }
}
