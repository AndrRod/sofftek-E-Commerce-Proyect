package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.mapper.manager.CustomizationAllowedMapper;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
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
    public SellerCustomizationDto getDtoFromEntity(SellerCustomization customization){
        return new SellerCustomizationDto(customization.getId(), customization.getName(), customization.getCustomizationPrice());
    }
    public SellerCustomizationCompleteDto getCompleteDtoFromEntity(SellerCustomization customization){
        return new SellerCustomizationCompleteDto(customization.getId(), customization.getCustomizationAllowed().getType(), customization.getName(), customization.getCustomizationPrice());
//        customizationAllowedMapper.getDtoFromEntity(customization.getCustomizationAllowed())
    }
    public List<SellerCustomizationCompleteDto> getCompleteDtoListFromEntityList(List<SellerCustomization> customizations){
        return customizations.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
    public SellerCustomization updateEntityFromDto(SellerCustomization customization, SellerCustomizationDto sellerCustomizationDto){
        Stream.of(sellerCustomizationDto).forEach((sc)->{
            if(sc.getCustomizationPrice()!=null) customization.setCustomizationPrice(sc.getCustomizationPrice());
            if(sc.getName()!=null) customization.setName(sc.getName());
        });
        return customization;
    }

    public List<SellerCustomizationDto> getDtoListFromEntityList(List<SellerCustomization> customizations) {
        return customizations.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}
