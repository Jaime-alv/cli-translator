package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jaime.translator.mapping.ResponseInterface;

public class QuotaResponse implements ResponseInterface {

    /*
     * {"character_count":1400,"character_limit":500000}
     */

    @JsonProperty("character_count")
    int charCount;

    /*
     * Default limit for free users is 500.000 chars
     */
    @JsonProperty("character_limit")
    int charLimit;

    QuotaResponse() {}

    QuotaResponse(int charCount) {
        this.charCount = charCount;
        this.charLimit = 500000;
    }

    public QuotaResponse(int charCount, int charLimit) {
        this.charCount = charCount;
        this.charLimit = charLimit;
    }

    @Override
    public String getText() {
        String quota = formatQuota(getQuotaPercentage());
        return String.format("Quota: %1$s/%2$s >> %3$s%%", this.charCount, this.charLimit, quota);
    }

    float getQuotaPercentage() {
        return (this.charCount * 100) / (float) this.charLimit;
    }

    String formatQuota(float value) {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuotaResponse other = (QuotaResponse) obj;
        if (charCount != other.charCount)
            return false;
        if (charLimit != other.charLimit)
            return false;
        return true;
    }

}
