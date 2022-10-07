package com.LicuadoraProyectoEcommerce.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {
    @Schema(name = "email", type = "String", example = "rodrigueza.federacion@gmail.com")
    @NotBlank(message = "can't be null or empty")
    private String email;
    @Schema(name = "password", type = "String", example = "12345678")
    @NotBlank(message = "can't be null or empty")
    private String password;
}