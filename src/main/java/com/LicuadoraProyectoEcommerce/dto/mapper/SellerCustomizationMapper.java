package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SellerCustomizationMapper {
    @Autowired
    private CustomizationAllowedMapper customizationAllowedMapper;
    public SellerCustomizationCompleteDto getDtoFromEntity(SellerCustomization customization){
        return new SellerCustomizationCompleteDto(customization.getId(), customizationAllowedMapper.getDtoFromEntity(customization.getCustomizationAllowed()), customization.getName(), customization.getCustomizationPrice());
    }
    public List<SellerCustomizationCompleteDto> getDtoListFromEntityList(List<SellerCustomization> customizations){
        return customizations.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public SellerCustomization updateEntityFromDto(SellerCustomization customization, SellerCustomizationDto sellerCustomizationDto){
        Stream.of(sellerCustomizationDto).forEach((sc)->{
            if(sc.getCustomizationPrice()!=null) customization.setCustomizationPrice(sc.getCustomizationPrice());
            if(sc.getName()!=null) customization.setName(sc.getName());
        });
        return customization;
    }
}
