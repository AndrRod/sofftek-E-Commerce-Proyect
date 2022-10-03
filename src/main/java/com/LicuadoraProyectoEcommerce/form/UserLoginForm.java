package com.LicuadoraProyectoEcommerce.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {
    @NotBlank(message = "can't be null or empty")
    private String email;
    @NotBlank(message = "can't be null or empty")
    private String password;
}