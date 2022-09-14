package com.LicuadoraProyectoEcommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class ManagerDto {
    private String name;
    private List<BaseProductDtoComplete> baseProductDtos;
}
