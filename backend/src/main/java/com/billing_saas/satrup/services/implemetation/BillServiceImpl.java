package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.BillRequest;
import com.billing_saas.satrup.dtos.BillItemRequest;

import com.billing_saas.satrup.entities.*;
import com.billing_saas.satrup.exceptions.BadRequestException;
import com.billing_saas.satrup.exceptions.NotFoundException;

import com.billing_saas.satrup.repositories.*;
import com.billing_saas.satrup.services.BillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepo billRepository;
    private final TenantRepo tenantRepository;
    private final CustomerRepo customerRepository;
    private final UserRepo userRepository;
    private final ItemRepo itemRepository;
    private final BillItemRepo billItemRepository;

    public BillServiceImpl(BillRepo billRepository,
                           TenantRepo tenantRepository,
                           CustomerRepo customerRepository,
                           UserRepo userRepository,
                           ItemRepo itemRepository,
                           BillItemRepo billItemRepository) {
        this.billRepository = billRepository;
        this.tenantRepository = tenantRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.billItemRepository = billItemRepository;
    }

    @Override
    @Transactional
    public Bill create(BillRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found"));

        billRepository.findByTenant_TenantIdAndBillNumber(request.getTenantId(), request.getBillNumber())
                .ifPresent(b -> { throw new BadRequestException("Bill number already exists for this tenant"); });

        Customer customer = null;
        if (request.getCustomerId() != null) {
            customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));
        }

        User createdBy = null;
        if (request.getCreatedBy() != null) {
            createdBy = userRepository.findById(request.getCreatedBy())
                    .orElseThrow(() -> new NotFoundException("User not found"));
        }

        Bill bill = new Bill();
        bill.setTenant(tenant);
        bill.setCustomer(customer);
        bill.setCreatedBy(createdBy);
        bill.setBillNumber(request.getBillNumber());
        bill.setDiscount(request.getDiscount() == null ? BigDecimal.ZERO : request.getDiscount());
        bill.setPaymentStatus(request.getPaymentStatus() == null ? "unpaid" : request.getPaymentStatus());
        bill.setPaymentMethod(request.getPaymentMethod());

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal gstAmount = BigDecimal.ZERO;

        for (BillItemRequest itemReq : request.getItems()) {
            Item item = itemRepository.findById(itemReq.getItemId())
                    .orElseThrow(() -> new NotFoundException("Item not found: " + itemReq.getItemId()));

            BigDecimal unitPrice = itemReq.getUnitPrice() != null ? itemReq.getUnitPrice() : item.getSalePrice();
            BigDecimal gstRate = itemReq.getGstRate() != null ? itemReq.getGstRate() : item.getGstRate();
            BigDecimal qty = itemReq.getQuantity();

            BigDecimal lineBase = unitPrice.multiply(qty);
            BigDecimal lineGst = lineBase.multiply(gstRate).divide(BigDecimal.valueOf(100));
            BigDecimal lineTotal = lineBase.add(lineGst);

            BillItem billItem = new BillItem();
            billItem.setBill(bill);
            billItem.setItem(item);
            billItem.setQuantity(qty);
            billItem.setUnitPrice(unitPrice);
            billItem.setGstRate(gstRate);
            billItem.setGstAmount(lineGst);
            billItem.setLineTotal(lineTotal);

            bill.getBillItems().add(billItem);

            subtotal = subtotal.add(lineBase);
            gstAmount = gstAmount.add(lineGst);
        }

        BigDecimal total = subtotal.add(gstAmount).subtract(bill.getDiscount());
        bill.setSubtotal(subtotal);
        bill.setGstAmount(gstAmount);
        bill.setTotalAmount(total);

        return billRepository.save(bill);
    }

    @Override
    public Bill getById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bill not found"));
    }

    @Override
    public List<Bill> getByTenant(Long tenantId) {
        return billRepository.findByTenant_TenantId(tenantId);
    }
}