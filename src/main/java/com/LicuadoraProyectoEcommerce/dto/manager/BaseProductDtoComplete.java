package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
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
    private Integer daysToManufacture;
    private String manager;
    private List<EnabledAreaCompleteDto> enabledAreas;
}
