package com.LicuadoraProyectoEcommerce.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @Builder
public class CustomizationAllowedDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "type", example = "color", description = "type: cannot allow null")
    private String type;
}
