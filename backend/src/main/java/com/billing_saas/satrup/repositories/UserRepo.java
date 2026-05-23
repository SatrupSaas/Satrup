package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByTenant_TenantIdAndPhone(Long tenantId, String phone);
    List<User> findByTenant_TenantId(Long tenantId);
}