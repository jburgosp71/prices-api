package com.bcnc.pricesapi.domain.exception;

public class InvalidBrandIdException extends RuntimeException {
    public InvalidBrandIdException() {
        super("Invalid brand id");
    }
}
