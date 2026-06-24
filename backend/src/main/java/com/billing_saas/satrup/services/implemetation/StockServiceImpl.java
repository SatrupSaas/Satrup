package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.StockRequest;
import com.billing_saas.satrup.entities.Item;
import com.billing_saas.satrup.entities.Stock;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.ItemRepo;
import com.billing_saas.satrup.repositories.StockRepo;
import com.billing_saas.satrup.repositories.TenantRepo;
import com.billing_saas.satrup.services.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepo stockRepository;
    private final ItemRepo itemRepository;
    private final TenantRepo tenantRepository;

    public StockServiceImpl(StockRepo stockRepository, ItemRepo itemRepository, TenantRepo tenantRepository) {
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Stock create(StockRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundException("Item not found"));

        Stock stock = new Stock();
        stock.setTenant(tenant);
        stock.setItem(item);

        return stockRepository.save(stock);
    }

    @Override
    public Stock getById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Stock not found"));
    }

    @Override
    public List<Stock> getByTenant(Long tenantId) {
        return stockRepository.findByTenant_TenantId(tenantId);
    }

    @Override
    public Stock update(Long id, StockRequest request) {
        Stock stock = getById(id);
        return stockRepository.save(stock);
    }

    @Override
    public void delete(Long id) {
        Stock stock = getById(id);
        stockRepository.delete(stock);
    }
}
