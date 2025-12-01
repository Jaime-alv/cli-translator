package com.github.jaime.translator.configuration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;

public class TestCommandLineParser {

    private CommandLineService cmd = new CommandLineService();

    private String[] DEFAULT = { "-mode", "spelling", "-text", "Hello world", "-api-key", "token" };

    private String[] addArg(String[] args) {
        Stream<String> defaultArgs = Arrays.stream(DEFAULT);
        Stream<String> newArgs = Arrays.stream(args);
        return Stream.concat(defaultArgs, newArgs).toArray(String[]::new);
    }

    @Test
    void shouldReturnValue() throws APIException {
        assertEquals("spelling", cmd.parse(DEFAULT).getApiMode());
    }

    @Test
    void shouldThrowException() {
        String[] args = new String[] { "mode", "spelling" };
        assertThrows(ParserException.class, () -> {
            cmd.parse(args);
        });
    }

    @Test
    void shouldReturnTargetLanguage() throws APIException {
        String[] args = new String[] { "-target", "ES" };
        String[] newArgs = addArg(args);
        assertEquals("ES", cmd.parse(newArgs).getTargetLanguage());
    }

    @Test
    void shouldReturnDefault() throws APIException {
        assertEquals(null, cmd.parse(DEFAULT).getTargetLanguage());
    }

    @Test
    void shouldNotThrowException() {
        assertDoesNotThrow(() -> cmd.parse(DEFAULT));
    }

    @Test
    void shouldReturnMessage() throws APIException {
        assertEquals("Hello world", cmd.parse(DEFAULT).getMessage());
    }

}
