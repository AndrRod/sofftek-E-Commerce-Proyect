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
    private BaseProductDtoComplete baseProduct;
    private Double basePrice;
    private Double finalPrice;
    private List<SellerAreaDto> areas;
}
