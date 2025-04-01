package com.bcnc.pricesapi.adapter.persistence;

import com.bcnc.pricesapi.domain.model.Price;
import com.bcnc.pricesapi.adapter.persistence.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void testGetPrice_PriceExists() {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.of(2023, 6, 14, 10, 0);
        Price price = new Price();

        when(priceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.of(price));

        Optional<Price> result = priceRepositoryAdapter.getPrice(productId, brandId, date);

        assertTrue(result.isPresent());
        assertEquals(price, result.get());
    }

    @Test
    void testGetPrice_PriceNotFound() {
        Long productId = 35455L;
        Integer brandId = 999;
        LocalDateTime date = LocalDateTime.of(2023, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryAdapter.getPrice(productId, brandId, date);

        assertFalse(result.isPresent());
    }
}
