package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Optional<Item> findByTenant_TenantIdAndItemNameIgnoreCase(Long tenantId, String itemName);
    List<Item> findByTenant_TenantId(Long tenantId);
}