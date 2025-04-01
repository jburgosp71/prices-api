package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.application.port.out.LoadBrandPort;
import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.domain.exception.BrandNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandServiceTest {
    @Mock
    private LoadBrandPort loadBrandPort;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBrandById_BrandExists() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        when(loadBrandPort.findBrandById(1)).thenReturn(Optional.of(brand));

        Brand result = brandService.getBrandById(1);

        assertNotNull(result);
        assertEquals("ZARA", result.getName());
    }

    @Test
    void testGetBrandById_BrandNotFound() {
        when(loadBrandPort.findBrandById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BrandNotFoundException.class, () -> brandService.getBrandById(1));

        assertEquals("Brand not found with id: 1", exception.getMessage());
    }
}