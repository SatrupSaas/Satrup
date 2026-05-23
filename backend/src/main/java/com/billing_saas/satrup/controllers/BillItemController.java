package com.billing_saas.satrup.controllers;

import com.billing_saas.satrup.common.ApiResponse;
import com.billing_saas.satrup.dtos.BillItemResponse;
import com.billing_saas.satrup.entities.BillItem;
import com.billing_saas.satrup.exceptions.NotFoundException;
import com.billing_saas.satrup.repositories.BillItemRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bill-items")
public class BillItemController {

    private final BillItemRepo billItemRepository;

    public BillItemController(BillItemRepo billItemRepository) {
        this.billItemRepository = billItemRepository;
    }

    @GetMapping("/{id}")
    public ApiResponse<BillItemResponse> getById(@PathVariable Long id) {
        BillItem billItem = billItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bill item not found"));
        return ApiResponse.success("Bill item found", toResponse(billItem));
    }

    @GetMapping("/bill/{billId}")
    public ApiResponse<List<BillItemResponse>> getByBillId(@PathVariable Long billId) {
        List<BillItemResponse> items = billItemRepository.findByBill_BillId(billId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ApiResponse.success("Bill items found", items);
    }

    @GetMapping("/item/{itemId}")
    public ApiResponse<List<BillItemResponse>> getByItemId(@PathVariable Long itemId) {
        List<BillItemResponse> items = billItemRepository.findByItem_ItemId(itemId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ApiResponse.success("Bill item records found", items);
    }

    private BillItemResponse toResponse(BillItem billItem) {
        BillItemResponse response = new BillItemResponse();
        response.setBillItemId(billItem.getBillItemId());
        response.setBillId(billItem.getBill().getBillId());
        response.setItemId(billItem.getItem().getItemId());
        response.setStockId(billItem.getStock() != null ? billItem.getStock().getStockId() : null);
        response.setItemName(billItem.getItem().getItemName());
        response.setQuantity(billItem.getQuantity());
        response.setUnitPrice(billItem.getUnitPrice());
        response.setGstRate(billItem.getGstRate());
        response.setGstAmount(billItem.getGstAmount());
        response.setLineTotal(billItem.getLineTotal());
        return response;
    }
}