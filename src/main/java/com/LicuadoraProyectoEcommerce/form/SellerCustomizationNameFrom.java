package com.LicuadoraProyectoEcommerce.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class SellerCustomizationNameFrom {
    @Schema(name = "name", type = "String", example = "azul")
    private String name;
}
