package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShoppingCartDto {
    private Long id;
    @JsonProperty("buyer name")
    private String buyerName;
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @NotBlank(message = "cant be empty or null")
    @JsonProperty("buyer dni")
    private String buyerDni;
    @JsonProperty("final price")
    private Double finalPrice;
}