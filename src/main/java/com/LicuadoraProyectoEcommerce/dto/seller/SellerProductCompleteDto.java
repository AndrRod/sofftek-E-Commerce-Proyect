package com.LicuadoraProyectoEcommerce.dto.seller;

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
    private Double finalPrice;
    private List<SellerAreaCompleteDto> areas;
}
