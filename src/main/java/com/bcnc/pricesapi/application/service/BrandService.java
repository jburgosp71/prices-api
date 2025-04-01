package com.bcnc.pricesapi.application.service;

import com.bcnc.pricesapi.application.port.in.BrandQueryUseCase;
import com.bcnc.pricesapi.application.port.out.LoadBrandPort;
import com.bcnc.pricesapi.domain.model.Brand;
import com.bcnc.pricesapi.domain.exception.BrandNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements BrandQueryUseCase {
    private final LoadBrandPort loadBrandPort;

    public BrandService(LoadBrandPort loadBrandPort) {
        this.loadBrandPort = loadBrandPort;
    }

    public Brand getBrandById(Integer brandId) {
        return loadBrandPort.findBrandById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + brandId));
    }
}
