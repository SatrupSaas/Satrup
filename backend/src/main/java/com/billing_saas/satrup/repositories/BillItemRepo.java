package com.billing_saas.satrup.repositories;

import com.billing_saas.satrup.entities.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillItemRepo extends JpaRepository<BillItem, Long> {
    List<BillItem> findByBill_BillId(Long billId);
    List<BillItem> findByItem_ItemId(Long itemId);
}