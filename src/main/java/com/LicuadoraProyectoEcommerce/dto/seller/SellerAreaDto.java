package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter@Setter @AllArgsConstructor
public class SellerAreaDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "name", example = "parte total")
    private EnabledAreaDto area;
}
