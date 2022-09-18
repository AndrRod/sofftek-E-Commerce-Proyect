package com.LicuadoraProyectoEcommerce.dto;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerProductDto {
    private Long id;
    private BaseProductDtoComplete baseProduct;
    private Double basePrice;
}