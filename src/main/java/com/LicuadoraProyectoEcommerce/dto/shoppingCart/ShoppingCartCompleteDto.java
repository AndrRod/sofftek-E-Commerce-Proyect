package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShoppingCartCompleteDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "buyer name", example = "Juan")
    @JsonProperty("buyer name")
    private String buyerName;
    @Schema(name = "buyer email", example = "juan@gmail.com")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @Schema(name = "buyer name", example = "33.064.279")
    @JsonProperty("buyer dni")
    private String buyerDni;
    @Schema(name = "final purchase price", example = "152000")
    @JsonProperty("final purchase price")
    private Double finalPrice;
    @JsonProperty("products ordered")
    private List<ProductDto> productsOrdered;
}
