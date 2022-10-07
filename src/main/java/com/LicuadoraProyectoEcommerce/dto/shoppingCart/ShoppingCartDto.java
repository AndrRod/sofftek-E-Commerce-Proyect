package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShoppingCartDto {
    @Schema(name = "id", example = "32", hidden = true)
    private Long id;
    @Schema(name = "buyer name", example = "andres comprador")
    @JsonProperty("buyer name")
    private String buyerName;
    @Schema(name = "buyer name", example = "andres@gmail.com")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @Schema(name = "buyer name", example = "33.064.279")
    @JsonProperty("buyer dni")
    private String buyerDni;
    @Schema(name = "buyer name", example = "14000", hidden = true)
    @JsonProperty("final purchase price")
    private Double finalPrice;
}
