package com.bcnc.pricesapi.domain.exception;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException() {
        super("Missing required parameters");
    }
}
