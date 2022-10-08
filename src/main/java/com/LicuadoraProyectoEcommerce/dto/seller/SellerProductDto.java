package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class SellerProductDto {
    @Schema(name = "id", type = "long", hidden = true)
    private Long id;
    @Schema(name = "name", example = "zapatilla de futbol")
    private String name;
    @Schema(name = "description", example = "zapatilla deporitva de color blanca tamaño 39 apta para jugar al tenis")
    private String description;
    @Schema(name = "final price", example = "12000")
    @JsonProperty("final price")
    private Double finalPrice;
    @Schema(name = "seller creator", example = "facundoVendedor@gmail.com")
    @JsonProperty("seller creator email")
    private String sellerCreatorEmail;
}
