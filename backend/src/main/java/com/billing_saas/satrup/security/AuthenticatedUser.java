package com.billing_saas.satrup.security;

public class AuthenticatedUser {
    private String userId;
    private String tenantId;
    private String role;

    public AuthenticatedUser(String userId, String tenantId, String role) {
        this.userId = userId;
        this.tenantId = tenantId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}