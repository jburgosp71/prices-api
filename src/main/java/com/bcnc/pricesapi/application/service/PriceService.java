package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.domain.model.Price;
import com.bcnc.pricesapi.adapter.web.dto.PriceResponse;
import com.bcnc.pricesapi.domain.exception.PriceNotFoundException;
import com.bcnc.pricesapi.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceResponse getPrice(Long productId, Integer brandId, LocalDateTime date) {
        Price price = priceRepository.findApplicablePrice(productId, brandId, date)
                .orElseThrow(() -> new PriceNotFoundException("No price found for product " + productId + " and brand " + brandId + " at " + date));

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
}
