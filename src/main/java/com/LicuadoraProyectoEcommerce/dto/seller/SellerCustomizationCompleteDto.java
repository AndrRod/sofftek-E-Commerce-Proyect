package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter @AllArgsConstructor
public class SellerCustomizationCompleteDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "customization", example = "color")
    @JsonProperty("customization")
    private String customizationAllowed;
    @Schema(name = "name", example = "azul")
    private String name;
    @Schema(name = "customization price", example = "200.0")
    @JsonProperty("customization price")
    private Double customizationPrice;
}
