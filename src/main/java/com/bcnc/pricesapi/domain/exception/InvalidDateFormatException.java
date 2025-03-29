package com.bcnc.pricesapi.domain.exception;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException() {
        super("The format of the date parameter must be: yyyy-mm-ddThh:mi:ssZ");
    }
}
