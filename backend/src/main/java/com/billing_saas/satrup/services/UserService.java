package com.billing_saas.satrup.services;

import com.billing_saas.satrup.dtos.UserRequest;
import com.billing_saas.satrup.entities.User;

import java.util.List;

public interface UserService {
    User create(UserRequest request);
    User getById(Long id);
    List<User> getByTenant(Long tenantId);
}