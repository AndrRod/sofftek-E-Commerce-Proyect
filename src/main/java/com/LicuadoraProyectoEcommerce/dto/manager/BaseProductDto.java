package com.LicuadoraProyectoEcommerce.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseProductDto {
    private Long id;
    private String name;
    private Double estimatedPrice;
    private Integer daysToManufacture;

}

