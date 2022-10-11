package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor @Builder
public class BaseProductDto {
    @Schema(name = "id", hidden = true)
    private Long id;
    @Schema(name = "name", example = "zapatilla de futbol")
    @NotBlank(message = "can't be null or empty")
    private String name;
    @Schema(name = "price", example = "12000")
    @NotNull(message = "can't be null or empty")
    @JsonProperty("price")
    private Double price;
    @Schema(name = "days to manufacture", example = "12")
    @NotNull(message = "can't be null or empty")
    @JsonProperty("days to manufacture")
    private Integer daysToManufacture;

}

