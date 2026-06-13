package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByTenant_TenantIdAndPhone(Long tenantId, String phone);
    List<Customer> findByTenant_TenantId(Long tenantId);
}