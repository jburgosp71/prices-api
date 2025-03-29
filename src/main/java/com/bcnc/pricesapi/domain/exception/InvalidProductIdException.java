package com.bcnc.pricesapi.domain.exception;

public class InvalidProductIdException extends RuntimeException {
  public InvalidProductIdException() {
    super("Invalid product id");
  }
}
