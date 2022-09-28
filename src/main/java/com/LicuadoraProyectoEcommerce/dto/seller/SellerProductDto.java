package com.LicuadoraProyectoEcommerce.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class SellerProductDto {
    private Long id;
    private String name;
    private String description;
    private Double finalPrice;
}
