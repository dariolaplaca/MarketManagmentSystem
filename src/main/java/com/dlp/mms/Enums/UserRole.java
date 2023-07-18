package com.dlp.mms.Enums;

public enum UserRole {
    ADMIN("ADMIN"),
    WORKER("WORKER"),
    CASHIER("CASHIER"),
    CUSTOMER("CUSTOMER");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
