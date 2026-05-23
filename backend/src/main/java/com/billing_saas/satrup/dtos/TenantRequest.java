package com.billing_saas.satrup.dtos;

import jakarta.validation.constraints.NotBlank;

public class TenantRequest {
    @NotBlank
    private String shopName;
    @NotBlank
    private String phone;
    @NotBlank
    private String tradeCategory;

    private String planType;
    private String gstin;
    private String language;

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
}