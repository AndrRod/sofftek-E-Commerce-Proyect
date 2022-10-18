package com.LicuadoraProyectoEcommerce.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor @Builder @NoArgsConstructor
public class CustomizationAllowedDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "type", example = "color", description = "type: cannot allow null")
    private String type;
}
