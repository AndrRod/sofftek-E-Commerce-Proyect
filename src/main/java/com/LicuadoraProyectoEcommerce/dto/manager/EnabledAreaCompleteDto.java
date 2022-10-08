package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnabledAreaCompleteDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "name", example = "parte total")
    private String name;
    private List<CustomizationAllowedDto> customizationsAllowed;
}
