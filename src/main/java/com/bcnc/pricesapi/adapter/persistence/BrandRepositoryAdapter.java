package com.bcnc.pricesapi.adapter.persistence;

import com.bcnc.pricesapi.application.port.out.LoadBrandPort;
import com.bcnc.pricesapi.adapter.persistence.repository.BrandRepository;
import com.bcnc.pricesapi.domain.model.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandRepositoryAdapter implements LoadBrandPort {
    private final BrandRepository brandRepository;

    public BrandRepositoryAdapter(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Brand> findBrandById(Integer brandId) {
        return brandRepository.findById(brandId);
    }
}
