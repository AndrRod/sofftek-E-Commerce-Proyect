package com.LicuadoraProyectoEcommerce.dto.userAuth;

import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
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
