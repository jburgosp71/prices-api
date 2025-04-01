package com.bcnc.pricesapi.adapter.persistence;

import com.bcnc.pricesapi.adapter.persistence.repository.PriceRepository;
import com.bcnc.pricesapi.application.port.out.LoadPricePort;
import com.bcnc.pricesapi.domain.model.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryAdapter implements LoadPricePort {
    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPrice(Long productId, Integer brandId, LocalDateTime date) {
        return priceRepository.findApplicablePrice(productId, brandId, date);
    }
}
