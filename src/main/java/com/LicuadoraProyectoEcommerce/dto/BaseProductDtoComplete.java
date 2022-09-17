package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BaseProductDtoComplete {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer daysToManufacture;
    private String manager;
    private List<EnabledAreaDto> enabledAreasDto;
}
