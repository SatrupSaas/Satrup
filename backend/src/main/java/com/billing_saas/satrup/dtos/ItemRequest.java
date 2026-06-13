package com.billing_saas.satrup.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemRequest {
    @NotNull
    private Long tenantId;
    @NotBlank
    private String itemName;
    private String category;
    private String hsnCode;
    private BigDecimal mrp;
    @NotNull
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private BigDecimal gstRate;
    private BigDecimal currentStock;
    private BigDecimal lowStockLimit;
    private String unit;

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getHsnCode() { return hsnCode; }
    public void setHsnCode(String hsnCode) { this.hsnCode = hsnCode; }
    public BigDecimal getMrp() { return mrp; }
    public void setMrp(BigDecimal mrp) { this.mrp = mrp; }
    public BigDecimal getSalePrice() { return salePrice; }
    public void setSalePrice(BigDecimal salePrice) { this.salePrice = salePrice; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public BigDecimal getGstRate() { return gstRate; }
    public void setGstRate(BigDecimal gstRate) { this.gstRate = gstRate; }
    public BigDecimal getCurrentStock() { return currentStock; }
    public void setCurrentStock(BigDecimal currentStock) { this.currentStock = currentStock; }
    public BigDecimal getLowStockLimit() { return lowStockLimit; }
    public void setLowStockLimit(BigDecimal lowStockLimit) { this.lowStockLimit = lowStockLimit; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}