package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.BillRequest;
import com.billing_saas.satrup.entities.Bill;

import java.util.List;

public interface BillService {
    Bill create(BillRequest request);
    Bill getById(Long id);
    List<Bill> getByTenant(Long tenantId);
}