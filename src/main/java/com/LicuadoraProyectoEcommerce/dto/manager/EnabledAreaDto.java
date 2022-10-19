package com.LicuadoraProyectoEcommerce.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class EnabledAreaDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "name", example = "parte total")
    private String name;
}
