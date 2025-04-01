package com.bcnc.pricesapi.application.port.in;

import com.bcnc.pricesapi.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceQueryUseCase {
    Price findPrice(Long productId, Integer brandId, LocalDateTime date);
}
