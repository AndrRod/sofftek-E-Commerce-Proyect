package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter@Setter @AllArgsConstructor
public class SellerAreaDto {
    private Long id;
    private EnabledAreaDto area;
}
