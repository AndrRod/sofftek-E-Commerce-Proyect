package com.LicuadoraProyectoEcommerce.dto.shoppingCart;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "name", example = "zapatilla de futbol")
    private String name;
    @Schema(name = "description", example = "zapatilla deporitva de color blanca tamaño 39 apta para jugar al futbol")
    private String description;
    @Schema(name = "final price", example = "120000")
    @JsonProperty("final price")
    private Double finalPrice;
    @Schema(name = "amount products", example = "2")
    @JsonProperty("amount products")
    private Integer amountProducts;

}
