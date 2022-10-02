package com.LicuadoraProyectoEcommerce.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterForm {
    private Long id;
    @Pattern(regexp = "([A-Z]{1}[a-zØ-öø-ÿ]{2,})(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?",
            message = "the name format is incorrect, and remember the names have to start with capital letters")
    private String name;
    @Pattern(regexp = "(?=.*[a-zA-Z_])(?!.*(\\s))(?!.*(\\_|@|\\-|\\.){2}).{4,25}@(gmail?|hotmail?|outlook?|yahoo?).(com{1})",
            message = "the email format is incorrect, the only mail accounts allowed are: gmail, hotmail, outlook and yahoo.")
    private String email;
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Pattern(regexp = "(?=.*\\d\\w)(?=.*[A-Z])|(?=.*[A-Z])(?=.*\\d\\w)(?!.*(.)\\1{2,})(?!.*\\s)(?!.*\\s).{8,}",
            message = "the password have to contain at least a letter lowercase and uppercase and a number")
    private String password;
}
