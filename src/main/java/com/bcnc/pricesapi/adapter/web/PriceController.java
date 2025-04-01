package com.bcnc.pricesapi.adapter.web;

import com.bcnc.pricesapi.adapter.web.dto.PriceResponse;
import com.bcnc.pricesapi.application.service.PriceService;
import com.bcnc.pricesapi.domain.exception.*;
import com.bcnc.pricesapi.application.service.BrandService;
import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.domain.model.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/price-search")
@Tag(name = "Prices API", description = "API for find prices")
public class PriceController {

    private final BrandService brandService;
    private final PriceService priceService;

    public PriceController(BrandService brandService, PriceService priceService) {
        this.brandService = brandService;
        this.priceService = priceService;
    }

    @Operation(summary = "Get prices", description = "Return price for a brand product at specific date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found price"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters or not included", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Brand or price not found", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public ResponseEntity<PriceResponse> priceSearch(
            @Parameter(description = "Date in ISO format (ej: 2025-04-01T12:00:00Z)", required = true)
            @RequestParam(name = "date", required = false) String dateStr,
            @Parameter(description = "Product ID", required = true)
            @RequestParam(name = "productid", required = false) String productIdStr,
            @Parameter(description = "Brand ID", required = true)
            @RequestParam(name = "brandid", required = false) String brandIdStr
    ) {

        validateParameters(dateStr, productIdStr, brandIdStr);
        LocalDateTime date = getDate(dateStr);
        Long productId = getProductId(productIdStr);
        Brand brand = getBrand(brandIdStr);

        return ResponseEntity.ok(getPrice(productId, brand, date));
    }

    private PriceResponse getPrice(Long productId, Brand brand, LocalDateTime date) {
        Price price = priceService.findPrice(productId, brand.getId(), date);
        return new PriceResponse(
                price.getProductId(),
                price.getBrand().getId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );
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
