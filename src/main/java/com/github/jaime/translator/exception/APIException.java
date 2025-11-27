package com.github.jaime.translator.exception;

public class APIException extends Exception {

    public APIException(String msg) {
        super(msg);
    }

    public APIException(String msg, Throwable err) {
        super(msg, err);
    }

}
