package com.LicuadoraProyectoEcommerce.dto.userAuth;

import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDtoComplete {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "name", example ="Andres Manager")
    private String name;
    @Schema(name = "email", example = "andres@gmail.com")
    private String email;
    @Schema(name = "password", example ="$2a$10$V5iTU2UGwxx6ZFWhobzleuJoxjk9vT3bWzY7ncR7mWUjVm1eb31cu")
    private String password;
    @Schema(name = "role", example = "SELLER")
    private Role role;
}
