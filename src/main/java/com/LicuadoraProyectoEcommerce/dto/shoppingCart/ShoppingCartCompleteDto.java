package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String buyerName;
    private String buyerEmail;
    private String buyerDni;
    private Double finalPrice;
    private List<ProductDto> productOrdered;
}
