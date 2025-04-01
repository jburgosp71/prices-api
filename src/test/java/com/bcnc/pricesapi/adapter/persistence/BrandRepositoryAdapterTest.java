package com.bcnc.pricesapi.adapter.persistence;

import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.adapter.persistence.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandRepositoryAdapterTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandRepositoryAdapter brandRepositoryAdapter;

    @Test
    void testFindBrandById_BrandExists() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));

        Optional<Brand> result = brandRepositoryAdapter.findBrandById(1);

        assertTrue(result.isPresent());
        assertEquals("ZARA", result.get().getName());
    }

    @Test
    void testFindBrandById_BrandNotFound() {
        when(brandRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Brand> result = brandRepositoryAdapter.findBrandById(999);

        assertFalse(result.isPresent());
    }
}
