package com.github.jaime.translator.mapping.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jaime.translator.mapping.ResponseInterface;

public class ErrorMessage  implements ResponseInterface{

    /* 
    ("{\"message\":\"Forbidden. You can find more info in our docs: https://developers.deepl.com/docs/getting-started/auth\"}"
    */

    @JsonProperty("message")
    String message;

    ErrorMessage() {}

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getText() {
        return this.message;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorMessage other = (ErrorMessage) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    };
}
