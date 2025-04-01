package com.bcnc.pricesapi.application.port.out;

import com.bcnc.pricesapi.domain.model.Brand;

import java.util.Optional;

public interface LoadBrandPort {
    Optional<Brand> findBrandById(Integer brandId);
}
