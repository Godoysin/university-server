package com.luxoft.producer.security.constants;

public enum RoleEnum {
    ROLE("testRole");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
