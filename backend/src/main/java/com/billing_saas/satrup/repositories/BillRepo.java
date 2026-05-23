package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepo extends JpaRepository<Bill, Long> {
    Optional<Bill> findByTenant_TenantIdAndBillNumber(Long tenantId, String billNumber);
    List<Bill> findByTenant_TenantId(Long tenantId);
}