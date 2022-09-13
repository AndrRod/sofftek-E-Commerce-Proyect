package com.LicuadoraProyectoEcommerce.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER(Code.MANAGER),
    SELLER(Code.SELLER);
    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String SELLER = "ROLE_SELLER";

    }
}