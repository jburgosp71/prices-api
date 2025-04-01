package com.bcnc.pricesapi.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "Response containing price information")
public class PriceResponse {
    @Schema(description = "Product ID", example = "35455")
    private Long productId;
    @Schema(description = "Brand ID", example = "1")
    private Integer brandId;
    @Schema(description = "Price list ID", example = "1")
    private Integer priceList;
    @Schema(description = "Price start date", example = "2025-04-01T12:00:00Z")
    private LocalDateTime startDate;
    @Schema(description = "Price end date", example = "2025-04-01T12:00:00Z")
    private LocalDateTime endDate;
    @Schema(description = "Price", example = "25.45")
    private BigDecimal price;
    @Schema(description = "Currency", example = "EUR")
    private String currency;
}
