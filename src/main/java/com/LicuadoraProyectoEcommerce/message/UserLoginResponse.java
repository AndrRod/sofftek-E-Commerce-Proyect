package com.LicuadoraProyectoEcommerce.message;

import com.LicuadoraProyectoEcommerce.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginResponse {
    private String email;
    private Role role;
    private String accessToken;
    private String refreshToken;
}