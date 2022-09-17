package com.LicuadoraProyectoEcommerce.dto;

import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SellerProductDto {
    private Long id;
    private BaseProductDtoComplete baseProduct;
    private List<SellerAreaDto> areas;
}
