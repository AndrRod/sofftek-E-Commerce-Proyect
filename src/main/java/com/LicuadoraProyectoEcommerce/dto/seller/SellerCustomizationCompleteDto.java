package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter @AllArgsConstructor
public class SellerCustomizationCompleteDto {
    private Long id;
//    private CustomizationAllowedDto customizationAllowed;
    @JsonProperty("customization")
    private String customizationAllowed;
    private String name;
    @JsonProperty("customization price")
    private Double customizationPrice;
}
