package com.LicuadoraProyectoEcommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseProductDtoComplete {
    private Long id;
    private String name;
    private Double estimatedPrice;
    private String description;
    private Integer daysToManufacture;
    private String manager;
    private List<EnabledAreaCompleteDto> enabledAreas;
}
