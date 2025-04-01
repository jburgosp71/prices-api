package com.bcnc.pricesapi.application.port.out;

import com.bcnc.pricesapi.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadPricePort {
    Optional<Price> getPrice(Long productId, Integer brandId, LocalDateTime date);
}
