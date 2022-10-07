package com.LicuadoraProyectoEcommerce.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class RoleNameForm {
    @Schema(name = "role_name", type = "String", example = "SELLER")
    @NotBlank(message = "can't be null or empty")
    private String role_name;
}
