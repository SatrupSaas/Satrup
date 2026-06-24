package com.billing_saas.satrup.dtos;

public class LoginResponse {
    private String token;
    private Long userId;
    private Long tenantId;
    private String name;
    private String phone;
    private String role;

    public LoginResponse(String token, Long userId, Long tenantId, String name, String phone, String role) {
        this.token = token;
        this.userId = userId;
        this.tenantId = tenantId;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
