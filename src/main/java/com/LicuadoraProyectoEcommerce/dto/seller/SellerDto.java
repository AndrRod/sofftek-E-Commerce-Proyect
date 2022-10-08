package com.LicuadoraProyectoEcommerce.dto.seller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerDto {
    @Schema(name = "id", type = "long", hidden = true)
    private Long id;
    @Schema(name = "name", example = "Facundo Ramirez", type = "String")
    private String name;
    @Schema(name = "email", type = "String", example = "facundoVendedor@gmail.com")
    private String email;
}
