package com.LicuadoraProyectoEcommerce.dto.userAuth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}
