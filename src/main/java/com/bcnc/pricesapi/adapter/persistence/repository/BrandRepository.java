package com.bcnc.pricesapi.adapter.persistence.repository;

import com.bcnc.pricesapi.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
