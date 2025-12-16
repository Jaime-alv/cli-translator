package com.github.jaime.translator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.mapping.json.ErrorMessage;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.service.impl.TranslateFromDeepL;

public class TestMain {


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

    // @ParameterizedTest
    // @ValueSource(strings = {"quota", "translate"})
    // void shouldCompleteCycle(String mode) {
    //     String[] args = { "-mode", mode, "-api-key", "token:fx" , "-text", "random"};
    //     assertDoesNotThrow(() -> Main.main(args));
    // }

    @SuppressWarnings("static-access")
    @Test
    void testTranslateFlow() throws APIException {        
        try (MockedStatic<Main> mocked = mockStatic(Main.class)) {
            ErrorMessage response = new ErrorMessage("BOO");
            Main mockedMain = spy(Main.class);
            TranslateFromDeepL mockedTranslate = mock(TranslateFromDeepL.class);
            when(mockedTranslate.getResponse()).thenReturn(response);
            when(mockedMain.serviceSelector(any(ConfigAdapter.class))).thenReturn(mockedTranslate);
            String[] args = { "-mode", "translate", "-api-key", "token:fx" , "-text", "random"};
            assertDoesNotThrow(() -> mockedMain.main(args));
        }
    }
}
