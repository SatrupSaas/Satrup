package com.billing_saas.satrup.dtos;

import java.time.LocalDateTime;

public class TenantResponse {
    private Long tenantId;
    private String shopName;
    private String phone;
    private String tradeCategory;
    private String planType;
    private String gstin;
    private String language;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getTradeCategory() { return tradeCategory; }
    public void setTradeCategory(String tradeCategory) { this.tradeCategory = tradeCategory; }
    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }
    public String getGstin() { return gstin; }
    public void setGstin(String gstin) { this.gstin = gstin; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}