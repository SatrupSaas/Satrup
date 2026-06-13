package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.ItemRequest;
import com.billing_saas.satrup.entities.Item;

import java.util.List;

public interface ItemService {
    Item create(ItemRequest request);
    Item getById(Long id);
    List<Item> getByTenant(Long tenantId);
}