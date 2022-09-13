package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseProductDto {
    private String name;
    private Double price;
    private String description;
    private Integer daysToManufacture;

}

