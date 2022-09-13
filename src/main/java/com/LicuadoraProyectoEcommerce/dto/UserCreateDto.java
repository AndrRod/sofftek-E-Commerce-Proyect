package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
}
