package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerCustomizationMapper {
    SellerCustomizationCompleteDto getDtoFromEntity(SellerCustomization customization){
        return new SellerCustomizationCompleteDto(customization.getId(), customization.getType(), customization.getName());
    }
    List<SellerCustomizationCompleteDto> getDtoListFromEntityList(List<SellerCustomization> customizations){
        return customizations.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    SellerCustomization updateEntityFromDto(SellerCustomization customization, SellerCustomizationDto sellerCustomizationDto){
        customization.setName(sellerCustomizationDto.getName());
        return customization;
    }
}
