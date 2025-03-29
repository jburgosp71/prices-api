package com.bcnc.pricesapi.application.exception;

import com.bcnc.pricesapi.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<String> handleMissingParameterException(MissingParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<String> handleInvalidDateFormatException(InvalidDateFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<String> handleInvalidProductIdException(InvalidProductIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidBrandIdException.class)
    public ResponseEntity<String> handleInvalidBrandIdException(InvalidBrandIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> handlePriceNotFoundException(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
