package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.CustomerRequest;
import com.billing_saas.satrup.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerRequest request);
    Customer getById(Long id);
    List<Customer> getByTenant(Long tenantId);
}