package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class SellerCustomizationDto {
    @Schema(name = "id", type = "long", hidden = true)
    private Long id;
    @Schema(name = "name", example = "azul", type = "String", description = "cannot allow null")
    private String name;
    @Schema(name = "customization price", example = "400", type = "Double", description = "cannot allow null")
    @JsonProperty("customization price")
    private Double customizationPrice;
}
