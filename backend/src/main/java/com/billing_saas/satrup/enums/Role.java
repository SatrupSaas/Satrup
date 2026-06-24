package com.billing_saas.satrup.enums;

public enum Role {
    OWNER("owner"),
    MANAGER("manager"),
    CASHIER("cashier"),
    DELIVERY("delivery");

    private final String dbValue;

    Role(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    // Helper to convert a DB string safely to this Enum
    public static Role fromString(String text) {
        for (Role r : Role.values()) {
            if (r.dbValue.equalsIgnoreCase(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + text);
    }
}