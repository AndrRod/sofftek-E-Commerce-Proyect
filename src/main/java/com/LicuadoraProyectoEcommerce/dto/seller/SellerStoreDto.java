package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SellerStoreDto {
    private Long id;
    @Schema(name = "name", example = "Andres Show Sport", type = "String", description = "cannot allow null")
    @NotBlank(message = "cant be empty or null")
    private String name;
    @Schema(name = "description", example = "Tienda dedicada a la venta de indumentaria deporitva", type = "String", description = "cannot allow null")
    @NotBlank(message = "cant be empty or null")
    private String description;
}
