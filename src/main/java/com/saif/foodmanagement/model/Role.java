package com.saif.foodmanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saifuzzaman
 */
public enum Role {

    ADMIN("ADMIN"),
    COOK("COOK"),
    USER("USER");

    private String type;

    Role(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        for(Role role : values()) {
            roles.add(role.name());
        }

        return roles;
    }
}
