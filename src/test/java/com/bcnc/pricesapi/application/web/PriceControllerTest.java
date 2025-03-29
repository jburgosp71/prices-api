package com.bcnc.pricesapi.application.web;

import com.bcnc.pricesapi.application.service.BrandService;
import com.bcnc.pricesapi.domain.exception.BrandNotFoundException;
import com.bcnc.pricesapi.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceControllerTest {
    
    @Mock
    private BrandService brandService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrices_BrandFound() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        when(brandService.getBrandById(1)).thenReturn(brand);

        ResponseEntity<String> response = priceController.prices(
                "2023-08-02T10:30:00Z",
                "35455",
                "1"
        );

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Brand found: ZARA", response.getBody());
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