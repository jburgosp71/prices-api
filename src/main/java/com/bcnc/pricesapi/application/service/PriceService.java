package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.application.port.in.PriceQueryUseCase;
import com.bcnc.pricesapi.application.port.out.LoadPricePort;
import com.bcnc.pricesapi.domain.model.Price;
import com.bcnc.pricesapi.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService implements PriceQueryUseCase {

    private final LoadPricePort loadPricePort;

    public PriceService(LoadPricePort loadPricePort) {
        this.loadPricePort = loadPricePort;
    }

    @Override
    public Price findPrice(Long productId, Integer brandId, LocalDateTime date) {
        return loadPricePort.getPrice(productId, brandId, date)
                .orElseThrow(() -> new PriceNotFoundException("No price found for product " + productId + " and brand " + brandId + " at " + date));
    }
}
