package com.billing_saas.satrup.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BillItemRequest {
    @NotNull
    private Long itemId;
    private Long stockId;
    @NotNull
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal gstRate;

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getGstRate() { return gstRate; }
    public void setGstRate(BigDecimal gstRate) { this.gstRate = gstRate; }
}