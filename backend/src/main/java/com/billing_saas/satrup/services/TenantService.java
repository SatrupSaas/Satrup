package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.TenantRequest;
import com.billing_saas.satrup.entities.Tenant;

import java.util.List;

public interface TenantService {
    Tenant create(TenantRequest request);
    Tenant getById(Long tenant_id);
    List<Tenant> getAll();
}