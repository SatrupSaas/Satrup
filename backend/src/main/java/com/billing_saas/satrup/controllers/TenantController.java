package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.TenantRequest;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.services.TenantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ApiResponse<Tenant> create(@Valid @RequestBody TenantRequest request) {
        return ApiResponse.success("Tenant created", tenantService.create(request));
    }

    @GetMapping("/{tenantId}")
    public ApiResponse<Tenant> getById(@PathVariable Long tenantId) {
        return ApiResponse.success("Tenant found", tenantService.getById(tenantId));
    }

    @GetMapping
    public ApiResponse<List<Tenant>> getAll() {
        return ApiResponse.success("Tenants found", tenantService.getAll());
    }
}