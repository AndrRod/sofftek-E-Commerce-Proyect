package com.LicuadoraProyectoEcommerce.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class SellerProductForm {
    @Schema(name = "base price", type = "Double", example = "1000")
    @NotNull(message = "can't be null or empty")
    @JsonProperty("base price")
    private Double basePrice;
    @Schema(name = "description", type = "String", example = "Esta es una zapatilla deportiva de color azul y del tamaño profesional")
    @NotBlank(message = "can't be null or empty")
    @Column(columnDefinition = "TEXT")
    private String description;
}
