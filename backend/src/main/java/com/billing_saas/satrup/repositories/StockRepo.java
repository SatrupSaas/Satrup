package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {
}