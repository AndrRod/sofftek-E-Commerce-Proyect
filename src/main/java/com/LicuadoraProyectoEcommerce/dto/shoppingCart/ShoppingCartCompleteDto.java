package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShoppingCartCompleteDto {
    private Long id;
    @JsonProperty("buyer name")
    private String buyerName;
    @JsonProperty("buyer email")
    private String buyerEmail;
    @JsonProperty("buyer dni")
    private String buyerDni;
    @JsonProperty("final price")
    private Double finalPrice;
    @JsonProperty("products ordered")
    private List<ProductDto> productsOrdered;
}
