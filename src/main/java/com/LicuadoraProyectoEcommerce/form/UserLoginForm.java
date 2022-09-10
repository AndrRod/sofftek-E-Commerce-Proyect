package com.LicuadoraProyectoEcommerce.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {
    @NotBlank(message = "can't be null or empty")
    @Email(regexp = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message="Email format error")
    private String email;
    @NotBlank(message = "can't be null or empty")
    @Size(min = 8, message = "The password must be at least 8 characters")
    private String password;
}