package com.LicuadoraProyectoEcommerce.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class ShoppingCartForm {
    @Schema(name = "buyer name", type = "String", example = "Victor Salvatierra")
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer name")
    private String buyerName;
    @Schema(name = "buyer email", type = "String", example = "victorS@gmail.com")
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @Schema(name = "buyer dni", type = "String", example = "40.556.897")
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer dni")
    private String buyerDni;
}
