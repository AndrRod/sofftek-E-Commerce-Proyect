package com.LicuadoraProyectoEcommerce.dto.seller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class SellerAreaCompleteDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "enabled area", example = "todo el producto")
    private String enabledArea;
    private List<SellerCustomizationCompleteDto> customizations;
}
