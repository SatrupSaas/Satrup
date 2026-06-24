package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.StockRequest;
import com.billing_saas.satrup.entities.Stock;
import java.util.List;

public interface StockService {
    Stock create(StockRequest request);
    Stock getById(Long id);
    List<Stock> getByTenant(Long tenantId);
    Stock update(Long id, StockRequest request);
    void delete(Long id);
}
