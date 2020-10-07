package com.finder.domain;

public enum Permission {
    READ("read"),
    WRITE("write"),
    GENERIC_USER("generic user");

    private final String description;

    Permission(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return name();
    }
}
