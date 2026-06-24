package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.TenantRequest;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.exceptions.BadRequestException;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.TenantRepo;
import com.billing_saas.satrup.services.TenantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepo tenantRepository;

    public TenantServiceImpl(TenantRepo tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant create(TenantRequest request) {
        tenantRepository.findByPhone(request.getPhone())
                .ifPresent(t -> { throw new BadRequestException("Tenant phone already exists"); });

        Tenant tenant = new Tenant();
        tenant.setShopName(request.getShopName());
        tenant.setPhone(request.getPhone());
        tenant.setTradeCategory(request.getTradeCategory());
        tenant.setPlanType(request.getPlanType() == null ? "free" : request.getPlanType());
        tenant.setGstin(request.getGstin());
        tenant.setLanguage(request.getLanguage() == null ? "hi" : request.getLanguage());
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant getById(Long tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new NotFoundException("Tenant not found"));
    }

    @Override
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }
}