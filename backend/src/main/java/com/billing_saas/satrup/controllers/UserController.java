package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.UserRequest;
import com.billing_saas.satrup.entities.User;
import com.billing_saas.satrup.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> create(@Valid @RequestBody UserRequest request) {
        return ApiResponse.success("User created", userService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getById(@PathVariable Long id) {
        return ApiResponse.success("User found", userService.getById(id));
    }

    @GetMapping("/tenant/{tenantId}")
    public ApiResponse<List<User>> getByTenant(@PathVariable Long tenantId) {
        return ApiResponse.success("Users found", userService.getByTenant(tenantId));
    }
}