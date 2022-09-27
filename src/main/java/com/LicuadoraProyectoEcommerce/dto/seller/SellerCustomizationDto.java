package com.LicuadoraProyectoEcommerce.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerCustomizationDto {
    private Long id;
    private String name;
    private Double customizationPrice;
}
