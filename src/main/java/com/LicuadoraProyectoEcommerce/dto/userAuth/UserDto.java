package com.LicuadoraProyectoEcommerce.dto.userAuth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    @Schema(name = "id", example = "1", type= "long", description = "id: autogenerated")
    private Long id;
    @Schema(name = "name", example = "Andres Rodriguez", type = "String", nullable = false, description = "name: cannot allow null")
    private String name;
    @Schema(name = "email", example = "andres@gmail.com", type = "String", description = "name: cannot allow null")
    private String email;
}
