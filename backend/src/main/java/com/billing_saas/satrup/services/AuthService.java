package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.LoginRequest;
import com.billing_saas.satrup.dtos.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
