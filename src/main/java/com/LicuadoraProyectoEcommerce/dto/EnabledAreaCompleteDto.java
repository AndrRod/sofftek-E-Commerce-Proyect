package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnabledAreaCompleteDto {
    private Long id;
    private String name;
    private List<CustomizationAllowedDto> customizationsAllowed;
}
