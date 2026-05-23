package com.billing_saas.satrup.services.impl;

import com.billing_saas.satrup.dtos.ItemRequest;
import com.billing_saas.satrup.entities.Item;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.ItemRepo;
import com.billing_saas.satrup.repositories.TenantRepo;
import com.billing_saas.satrup.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepository;
    private final TenantRepo tenantRepository;

    public ItemServiceImpl(ItemRepo itemRepository, TenantRepo tenantRepository) {
        this.itemRepository = itemRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Item create(ItemRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));

        Item item = new Item();
        item.setTenant(tenant);
        item.setItemName(request.getItemName());
        item.setCategory(request.getCategory());
        item.setHsnCode(request.getHsnCode());
        item.setMrp(request.getMrp());
        item.setSalePrice(request.getSalePrice());
        item.setPurchasePrice(request.getPurchasePrice());
        item.setGstRate(request.getGstRate() == null ? java.math.BigDecimal.ZERO : request.getGstRate());
        item.setCurrentStock(request.getCurrentStock() == null ? java.math.BigDecimal.ZERO : request.getCurrentStock());
        item.setLowStockLimit(request.getLowStockLimit() == null ? java.math.BigDecimal.TEN : request.getLowStockLimit());
        item.setUnit(request.getUnit() == null ? "piece" : request.getUnit());
        return itemRepository.save(item);
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
    }

    @Override
    public List<Item> getByTenant(Long tenantId) {
        return itemRepository.findByTenant_TenantId(tenantId);
    }
}