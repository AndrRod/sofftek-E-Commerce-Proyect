package com.LicuadoraProyectoEcommerce.model.userAuth;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER(Code.MANAGER),
    SELLER(Code.SELLER),
    NONE(Code.NONE),
    ADMIN(Code.ADMIN);
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

        public static final String NONE = "NONE";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}