package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnableAreaCompleteDto {
    private String name;
    private List<BaseProductDto> productList;
    private List<CustomizationAllowedDto> customizationsAllowed;
}
