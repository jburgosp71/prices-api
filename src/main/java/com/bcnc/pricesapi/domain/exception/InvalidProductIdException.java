package com.bcnc.pricesapi.domain.exception;

public class InvalidProductIdException extends RuntimeException {
  public InvalidProductIdException() {
    super("The productid parameter must be numeric");
  }
}
