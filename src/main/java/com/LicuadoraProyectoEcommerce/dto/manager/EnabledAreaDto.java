package com.LicuadoraProyectoEcommerce.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EnabledAreaDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "name", example = "parte total")
    private String name;
}
