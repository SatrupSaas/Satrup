package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.CustomerRequest;
import com.billing_saas.satrup.entities.Customer;
import com.billing_saas.satrup.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ApiResponse<Customer> create(@Valid @RequestBody CustomerRequest request) {
        return ApiResponse.success("Customer created", customerService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Customer> getById(@PathVariable Long id) {
        return ApiResponse.success("Customer found", customerService.getById(id));
    }

    @GetMapping("/tenant/{tenantId}")
    public ApiResponse<List<Customer>> getByTenant(@PathVariable Long tenantId) {
        return ApiResponse.success("Customers found", customerService.getByTenant(tenantId));
    }
}
