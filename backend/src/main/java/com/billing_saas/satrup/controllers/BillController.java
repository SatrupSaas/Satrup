package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.BillRequest;
import com.billing_saas.satrup.entities.Bill;
import com.billing_saas.satrup.services.BillService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ApiResponse<Bill> create(@Valid @RequestBody BillRequest request) {
        return ApiResponse.success("Bill created", billService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Bill> getById(@PathVariable Long id) {
        return ApiResponse.success("Bill found", billService.getById(id));
    }

    @GetMapping("/tenant/{tenantId}")
    public ApiResponse<List<Bill>> getByTenant(@PathVariable Long tenantId) {
        return ApiResponse.success("Bills found", billService.getByTenant(tenantId));
    }
}
