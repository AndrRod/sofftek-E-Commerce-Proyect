package com.LicuadoraProyectoEcommerce.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class SellerAreaCompleteDto {
    private Long id;
    private String enabledArea;
    private List<SellerCustomizationCompleteDto> customizations;
}
