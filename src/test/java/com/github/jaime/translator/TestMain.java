package com.github.jaime.translator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;

public class TestMain {

    private static Main mockMain;

    @BeforeAll
    static void setUp() throws APIException {
        // when(Main.serviceSelector(null)).thenReturn(null);
    }

    @Test
    void shouldFailOnRandomMode() {
        String[] args = { "-mode", "random", "-api-key", "token" };
        assertThrows(ParserException.class, () -> Main.main(args));
    }

    @Test
    void shouldFailOnWrongKey() {
        String[] args = { "-mode", "quota", "-api-key", "token" };
        assertThrows(InvalidKeyException.class, () -> Main.main(args));
    }

    @ParameterizedTest
    @ValueSource(strings = {"quota", "translate"})
    void shouldCompleteCycle(String mode) {
        String[] args = { "-mode", mode, "-api-key", "token:fx" , "-text", "random"};
        assertDoesNotThrow(() -> Main.main(args));
    }
}
