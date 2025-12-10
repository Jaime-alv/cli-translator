package com.github.jaime.translator.mapping.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestQuota {

    private static QuotaResponse quota = new QuotaResponse();

    @Test
    void shouldReturnAFloat() {
        assertEquals("0.28", quota.formatQuota(0.281f));
    }

    @ParameterizedTest
    @ValueSource(floats = {0.00f, 0.28f, 1.43434545f})
    void shouldRoundToTwoDecimals(float value) {
        assertEquals(4, quota.formatQuota(value).length());
    }

    @Test
    void shouldGet15percent() {
        quota.charCount = 75000;
        quota.charLimit = 500000;
        assertEquals("Quota: 75000/500000 >> 15.00%", quota.getText());
    }
}
