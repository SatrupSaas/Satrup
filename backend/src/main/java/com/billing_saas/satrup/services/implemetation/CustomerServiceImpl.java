package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.CustomerRequest;
import com.billing_saas.satrup.entities.Customer;
import com.billing_saas.satrup.entities.Tenant;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.CustomerRepo;
import com.billing_saas.satrup.repositories.TenantRepo;
import com.billing_saas.satrup.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepository;
    private final TenantRepo tenantRepository;

    public CustomerServiceImpl(CustomerRepo customerRepository, TenantRepo tenantRepository) {
        this.customerRepository = customerRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Customer create(CustomerRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));

        Customer customer = new Customer();
        customer.setTenant(tenant);
        customer.setCustomerName(request.getCustomerName());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public List<Customer> getByTenant(Long tenantId) {
        return customerRepository.findByTenant_TenantId(tenantId);
    }
}