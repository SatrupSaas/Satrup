package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepo extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByPhone(String phone);
}