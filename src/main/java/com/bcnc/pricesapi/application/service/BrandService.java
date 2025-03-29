package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.domain.exception.BrandNotFoundException;
import com.bcnc.pricesapi.domain.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getBrandById(Integer brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + brandId));
    }
}
