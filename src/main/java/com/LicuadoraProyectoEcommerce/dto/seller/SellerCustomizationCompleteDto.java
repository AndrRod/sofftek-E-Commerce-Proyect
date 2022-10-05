package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter @AllArgsConstructor
public class SellerCustomizationCompleteDto {
    private Long id;
    @JsonProperty("customization")
    private String customizationAllowed;
    private String name;
    @JsonProperty("customization price")
    private Double customizationPrice;
}
