package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SellerProductCompleteDto {
    @Schema(name = "id", type = "long")
    private Long id;
    @Schema(name = "name", example = "zapatilla de futbol")
    private String name;
    @Schema(name = "product description", example = "zapatilla deporitva de color blanca tamaño 39 apta para jugar al futbol")
    private String description;
    @Schema(name = "base price", example = "12000")
    @JsonProperty("base price")
    private Double basePrice;
    @Schema(name = "final price", example = "120000")
    @JsonProperty("final price")
    private Double finalPrice;
    private List<SellerAreaCompleteDto> areas;
}
