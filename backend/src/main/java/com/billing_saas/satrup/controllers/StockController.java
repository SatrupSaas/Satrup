package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.StockRequest;
import com.billing_saas.satrup.entities.Stock;
import com.billing_saas.satrup.services.StockService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ApiResponse<Stock> create(@Valid @RequestBody StockRequest request) {
        return ApiResponse.success("Stock created", stockService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Stock> getById(@PathVariable Long id) {
        return ApiResponse.success("Stock found", stockService.getById(id));
    }

    @GetMapping("/tenant/{tenantId}")
    public ApiResponse<List<Stock>> getByTenant(@PathVariable Long tenantId) {
        return ApiResponse.success("Stocks found", stockService.getByTenant(tenantId));
    }

    @PutMapping("/{id}")
    public ApiResponse<Stock> update(@PathVariable Long id, @Valid @RequestBody StockRequest request) {
        return ApiResponse.success("Stock updated", stockService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        stockService.delete(id);
        return ApiResponse.success("Stock deleted", "OK");
    }
}
