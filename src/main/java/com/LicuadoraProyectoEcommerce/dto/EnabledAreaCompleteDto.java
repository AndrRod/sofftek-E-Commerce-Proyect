package com.LicuadoraProyectoEcommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnabledAreaCompleteDto {
    private Long id;
    private String name;
    private List<CustomizationAllowedDto> customizationsAllowed;
}
