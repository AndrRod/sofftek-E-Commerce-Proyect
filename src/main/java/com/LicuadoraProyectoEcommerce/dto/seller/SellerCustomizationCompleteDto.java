package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter @AllArgsConstructor
public class SellerCustomizationCompleteDto {
    private Long id;
    private CustomizationAllowedDto customizationAllowed;
    private String name;
    private Double customizationPrice;
}
