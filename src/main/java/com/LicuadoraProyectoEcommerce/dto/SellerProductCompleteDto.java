package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SellerProductCompleteDto {
    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private Double finalPrice;
    private List<SellerAreaCompleteDto> areas;
}
