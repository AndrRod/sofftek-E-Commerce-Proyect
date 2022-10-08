package com.LicuadoraProyectoEcommerce.dto.manager;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ManagerDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "name", example = "Andres Rodriguez")
    private String name;
    @Schema(name = "name", example = "Andresrodriguez@gmail")
    private String email;
    private List<BaseProductDtoComplete> baseProductList;
}
