package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.LoginRequest;
import com.billing_saas.satrup.dtos.LoginResponse;
import com.billing_saas.satrup.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }

    @GetMapping("/validate")
    public ApiResponse<String> validateToken() {
        return ApiResponse.success("Token is valid", "OK");
    }
}
