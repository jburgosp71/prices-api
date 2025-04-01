package com.bcnc.pricesapi.adapter.persistence.repository;

import com.bcnc.pricesapi.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brand.id = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<Price> findApplicablePrice(Long productId, Integer brandId, LocalDateTime date);
}
