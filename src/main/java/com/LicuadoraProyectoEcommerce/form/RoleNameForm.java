package com.LicuadoraProyectoEcommerce.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class RoleNameForm {
    @NotBlank(message = "can't be null or empty")
    private String role_name;
}
