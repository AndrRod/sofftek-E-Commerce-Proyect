package com.LicuadoraProyectoEcommerce.dto;

import com.LicuadoraProyectoEcommerce.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDtoComplete {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
