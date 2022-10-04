package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class BaseProductDto {
    private Long id;
    @NotBlank(message = "can't be null or empty")
    private String name;
    @NotNull(message = "can't be null or empty")
    @JsonProperty("price")
    private Double price;
    @NotNull(message = "can't be null or empty")
    @JsonProperty("days to manufacture")
    private Integer daysToManufacture;

}

