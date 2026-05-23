package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepo extends JpaRepository<BillItem, Long> {
}