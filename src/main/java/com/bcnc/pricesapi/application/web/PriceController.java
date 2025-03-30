package com.bcnc.pricesapi.application.web;

import com.bcnc.pricesapi.application.dto.PriceResponse;
import com.bcnc.pricesapi.application.service.PriceService;
import com.bcnc.pricesapi.domain.exception.*;
import com.bcnc.pricesapi.application.service.BrandService;
import com.bcnc.pricesapi.domain.model.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final BrandService brandService;
    private final PriceService priceService;

    public PriceController(BrandService brandService, PriceService priceService) {
        this.brandService = brandService;
        this.priceService = priceService;
    }

    @GetMapping("/")
    public ResponseEntity<PriceResponse> prices(
            @RequestParam(name = "date", required = false) String dateStr,
            @RequestParam(name = "productid", required = false) String productIdStr,
            @RequestParam(name = "brandid", required = false) String brandIdStr
    ) {

        validateParameters(dateStr, productIdStr, brandIdStr);
        LocalDateTime date = getDate(dateStr);
        Long productId = getProductId(productIdStr);
        Brand brand = getBrand(brandIdStr);

        PriceResponse priceResponse = priceService.getPrice(productId, brand.getId(), date);

        return ResponseEntity.ok(priceResponse);
    }

    private void validateParameters(String dateStr, String productIdStr, String brandIdStr) {
        if (dateStr == null || productIdStr == null || brandIdStr == null) {
            throw new MissingParameterException();
        }
    }

    private LocalDateTime getDate(String dateStr) {
        try {
            return Instant.parse(dateStr).atZone(ZoneOffset.UTC).toLocalDateTime();
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    private Long getProductId(String productId) {
        try {
            return Long.parseLong(productId);
        } catch (NumberFormatException e) {
            throw new InvalidProductIdException();
        }
    }

    private Brand getBrand(String brandId) {
        try {
            return brandService.getBrandById(Integer.parseInt(brandId));
        } catch (NumberFormatException e) {
            throw new InvalidBrandIdException();
        }
    }
}
