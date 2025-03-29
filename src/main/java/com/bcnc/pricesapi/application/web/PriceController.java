package com.bcnc.pricesapi.application.web;

import com.bcnc.pricesapi.domain.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @GetMapping("/")
    public ResponseEntity<String> prices(
            @RequestParam(name = "date", required = false) String dateStr,
            @RequestParam(name = "productid", required = false) String productIdStr,
            @RequestParam(name = "brandid", required = false) String brandIdStr
    ) {
        // Validar si falta algún parámetro
        if (dateStr == null || productIdStr == null || brandIdStr == null) {
            throw new MissingParameterException();
        }

        // Validar formato de date
        try {
            Instant.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }

        // Validar productid numérico
        try {
            Long.parseLong(productIdStr);
        } catch (NumberFormatException e) {
            throw new InvalidProductIdException();
        }

        // Validar brandid numérico
        try {
            Integer.parseInt(brandIdStr);
        } catch (NumberFormatException e) {
            throw new InvalidBrandIdException();
        }

        return ResponseEntity.ok("OK request");
    }
}
