package com.bcnc.pricesapi.application.port.in;

import com.bcnc.pricesapi.domain.model.Brand;

public interface BrandQueryUseCase {
    Brand getBrandById(Integer brandId);
}
