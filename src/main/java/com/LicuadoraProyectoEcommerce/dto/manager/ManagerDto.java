package com.LicuadoraProyectoEcommerce.dto.manager;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private String name;
    private String email;
    private List<BaseProductDtoComplete> baseProductList;
}
