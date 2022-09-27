package com.LicuadoraProyectoEcommerce.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CustomizationAllowedCompleteDto {
    private Long id;
    private String type;
    private List<EnabledAreaDto> enabledAreas;
}
