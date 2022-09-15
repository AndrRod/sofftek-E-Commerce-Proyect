package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CustomizationAllowedCompleteDto {
    private String type;
    private String name;
    private Double price;
    private List<EnableAreaDto> enabledAreas;
}
