package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.ItemRequest;
import com.billing_saas.satrup.entities.Item;
import com.billing_saas.satrup.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ApiResponse<Item> create(@Valid @RequestBody ItemRequest request) {
        return ApiResponse.success("Item created", itemService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Item> getById(@PathVariable Long id) {
        return ApiResponse.success("Item found", itemService.getById(id));
    }

    @GetMapping("/tenant/{tenantId}")
    public ApiResponse<List<Item>> getByTenant(@PathVariable Long tenantId) {
        return ApiResponse.success("Items found", itemService.getByTenant(tenantId));
    }
}
