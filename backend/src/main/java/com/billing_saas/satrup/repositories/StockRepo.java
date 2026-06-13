package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock, Long> {
    List<Stock> findByTenant_TenantId(Long tenantId);
    List<Stock> findByItem_ItemId(Long itemId);
    List<Stock> findByTenant_TenantIdAndItem_ItemId(Long tenantId, Long itemId);
}