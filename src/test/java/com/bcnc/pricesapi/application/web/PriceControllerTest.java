package com.bcnc.pricesapi.application.web;

import com.bcnc.pricesapi.application.dto.PriceResponse;
import com.bcnc.pricesapi.application.service.BrandService;
import com.bcnc.pricesapi.application.service.PriceService;
import com.bcnc.pricesapi.domain.exception.BrandNotFoundException;
import com.bcnc.pricesapi.domain.exception.PriceNotFoundException;
import com.bcnc.pricesapi.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    @Mock
    private BrandService brandService;


    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrices_PriceFound() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        PriceResponse priceResponseDTO = new PriceResponse(
                35455L, 1, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59),
                BigDecimal.valueOf(35.50), "EUR"
        );

        when(brandService.getBrandById(1)).thenReturn(brand);
        when(priceService.getPrice(35455L, 1, LocalDateTime.of(2020, 6, 14, 16, 0)))
                .thenReturn(priceResponseDTO);

        ResponseEntity<PriceResponse> response = priceController.prices(
                "2020-06-14T16:00:00Z",
                "35455",
                "1"
        );

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(priceResponseDTO.getProductId(), response.getBody().getProductId());
        assertEquals(priceResponseDTO.getBrandId(), response.getBody().getBrandId());
        assertEquals(priceResponseDTO.getPrice(), response.getBody().getPrice());
        assertEquals(priceResponseDTO.getCurrency(), response.getBody().getCurrency());
    }

    @Test
    void testPrices_BrandNotFound() {
        when(brandService.getBrandById(2)).thenThrow(new BrandNotFoundException("Brand not found with id: 2"));

        Exception exception = assertThrows(BrandNotFoundException.class, () -> priceController.prices(
                "2023-08-02T10:30:00Z",
                "35455",
                "2"
        ));

        assertEquals("Brand not found with id: 2", exception.getMessage());
    }

    @Test
    void testPrices_PriceNotFound() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        when(brandService.getBrandById(1)).thenReturn(brand);
        when(priceService.getPrice(99999L, 1, LocalDateTime.of(2023, 8, 2, 10, 30)))
                .thenThrow(new PriceNotFoundException("No price found for product 99999 and brand 1"));

        Exception exception = assertThrows(PriceNotFoundException.class, () -> priceController.prices(
                "2023-08-02T10:30:00Z",
                "99999",
                "1"
        ));

        assertEquals("No price found for product 99999 and brand 1", exception.getMessage());
    }

    @Test
    void testPrices_MissingParameters() {
        Exception exception = assertThrows(RuntimeException.class, () -> priceController.prices(
                null,
                "35455",
                "1"
        ));

        assertEquals("Missing required parameters", exception.getMessage());
    }

    @Test
    void testPrices_InvalidDateFormat() {
        Exception exception = assertThrows(RuntimeException.class, () -> priceController.prices(
                "invalid-date",
                "35455",
                "1"
        ));

        assertEquals("Invalid date format", exception.getMessage());
    }

    @Test
    void testPrices_InvalidProductId() {
        Exception exception = assertThrows(RuntimeException.class, () -> priceController.prices(
                "2023-08-02T10:30:00Z",
                "invalid-product-id",
                "1"
        ));

        assertEquals("Invalid product id", exception.getMessage());
    }

    @Test
    void testPrices_InvalidBrandId() {
        Exception exception = assertThrows(RuntimeException.class, () -> priceController.prices(
                "2023-08-02T10:30:00Z",
                "35455",
                "invalid-brand-id"
        ));

        assertEquals("Invalid brand id", exception.getMessage());
    }

}