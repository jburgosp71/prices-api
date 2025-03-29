package com.bcnc.pricesapi.domain.exception;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException() {
        super("The request must be /prices/?date=yyyy-mm-ddThh:mi:ssZ&productid=xxxxxx&brandid=zz");
    }
}
