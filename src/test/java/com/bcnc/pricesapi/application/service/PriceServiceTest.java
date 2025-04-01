package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.application.port.out.LoadPricePort;
import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.domain.model.Price;
import com.bcnc.pricesapi.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {
    @Mock
    private LoadPricePort loadPricePort;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrice_Found() {
        Brand brand = new Brand();
        brand.setId(1);

        Price price = new Price();
        price.setProductId(35455L);
        price.setBrand(brand);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        when(loadPricePort.getPrice(35455L, 1, LocalDateTime.of(2020, 6, 14, 16, 0)))
                .thenReturn(Optional.of(price));

        Price response = priceService.findPrice(35455L, 1, LocalDateTime.of(2020, 6, 14, 16, 0));

        assertNotNull(response);
        assertEquals(35455L, response.getProductId());
        assertEquals(1, response.getBrand().getId());
        assertEquals(35.50, response.getPrice().doubleValue());
    }

    @Test
    void testGetPrice_NotFound() {
        when(loadPricePort.getPrice(99999L, 1, LocalDateTime.of(2020, 6, 14, 16, 0)))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.findPrice(99999L, 1, LocalDateTime.of(2020, 6, 14, 16, 0)));
    }

}