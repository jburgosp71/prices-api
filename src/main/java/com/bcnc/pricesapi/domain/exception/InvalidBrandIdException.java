package com.bcnc.pricesapi.domain.exception;

public class InvalidBrandIdException extends RuntimeException {
    public InvalidBrandIdException() {
        super("The brandid parameter must be numeric");
    }
}
