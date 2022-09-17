package com.LicuadoraProyectoEcommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
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
