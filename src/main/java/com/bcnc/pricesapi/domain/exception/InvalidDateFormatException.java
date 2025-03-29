package com.bcnc.pricesapi.domain.exception;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException() {
        super("Invalid date format");
    }
}
