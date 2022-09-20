package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseProductDto {
    private Long id;
    private String name;
    private Double estimatedPrice;
    private String description;
    private Integer daysToManufacture;

}

