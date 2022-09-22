package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class SellerProductDto {
    private Long id;
    private String name;
    private String description;
    private Double basePrice;
}
