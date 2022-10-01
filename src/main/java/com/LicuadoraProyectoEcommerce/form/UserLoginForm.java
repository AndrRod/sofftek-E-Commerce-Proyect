package com.LicuadoraProyectoEcommerce.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {
    @NotBlank(message = "can't be null or empty")
    @Pattern(regexp = "(?=.*[a-zA-Z_])(?!.*(\\s))(?!.*(\\_|@|\\-|\\.){2}).{4,25}@(gmail?|hotmail?|outlook?|yahoo?).(com{1})", message = "the email format is incorrect" +
            ", the only mail accounts allowed are: gmail, hotmail, outlook or yahoo.") //TODO PROBAR
    private String email;
    @NotBlank(message = "can't be null or empty")
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Pattern(regexp = "(?=.*\\d\\w)(?=.*[A-Z])|(?=.*[A-Z])(?=.*\\d\\w)(?!.*(.)\\1{2,})(?!.*\\s)(?!.*\\s).{8,}", message = "the password have to contain at least a capital letter and a number")
    private String password;
}