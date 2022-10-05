package com.LicuadoraProyectoEcommerce.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class ShoppingCartForm {
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer name")
    private String buyerName;
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer dni")
    private String buyerDni;
}
