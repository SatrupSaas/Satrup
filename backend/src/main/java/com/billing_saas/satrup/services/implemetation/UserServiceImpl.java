package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.UserRequest;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.entities.User;
import com.billing_saas.satrup.exceptions.BadRequestException;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.TenantRepo;
import com.billing_saas.satrup.repositories.UserRepo;
import com.billing_saas.satrup.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final TenantRepo tenantRepository;

    public UserServiceImpl(UserRepo userRepository, TenantRepo tenantRepository) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public User create(UserRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));

        userRepository.findByTenant_TenantIdAndPhone(request.getTenantId(), request.getPhone())
                .ifPresent(u -> { throw new BadRequestException("User phone already exists for this tenant"); });

        User user = new User();
        user.setTenant(tenant);
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole() == null ? "cashier" : request.getRole());
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getByTenant(Long tenantId) {
        return userRepository.findByTenant_TenantId(tenantId);
    }
}