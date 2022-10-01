package com.LicuadoraProyectoEcommerce.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterForm {
    @NotBlank(message = "can't be null or empty")
    @Pattern(regexp = "([A-Z]{1}[a-zØ-öø-ÿ]{2,})(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?", message = "the name format is incorrect, and remember the names have to start with capital letters") //TODO PROBAR
    private String name;
    @NotBlank(message = "can't be null or empty")
    @Pattern(regexp = "(?=.*[a-zA-Z_])(?!.*(\\s))(?!.*(\\_|@|\\-|\\.){2}).{4,25}@(gmail?|hotmail?|outlook?|yahoo?).(com{1})", message = "the email format is incorrect" +
            ", the only mail accounts allowed are: gmail, hotmail, outlook or yahoo.") //TODO PROBAR
    private String email;
    @NotBlank(message = "can't be null or empty")
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Pattern(regexp = "(?=.*\\d\\w)(?=.*[A-Z])|(?=.*[A-Z])(?=.*\\d\\w)(?!.*(.)\\1{2,})(?!.*\\s)(?!.*\\s).{5,8}")
    private String password;
}
