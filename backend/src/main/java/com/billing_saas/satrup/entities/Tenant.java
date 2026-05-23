package com.billing_saas.satrup.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "trade_category", nullable = false)
    private String tradeCategory;

    @Column(name = "plan_type", nullable = false)
    private String planType = "free";

    @Column(name = "gstin")
    private String gstin;

    @Column(name = "language", nullable = false)
    private String language = "hi";

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

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
}