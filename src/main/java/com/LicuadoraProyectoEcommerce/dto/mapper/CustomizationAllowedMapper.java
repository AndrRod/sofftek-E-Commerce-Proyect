package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.model.CustomizationAllowed;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomizationAllowedMapper {
    public CustomizationAllowedDto getDtoFromEntity(CustomizationAllowed customizationAllowed){
        return new CustomizationAllowedDto(customizationAllowed.getId(), customizationAllowed.getType());
    }
    public List<CustomizationAllowedDto> getListDtoFromListEntity(List<CustomizationAllowed> customizationAllowedList){
        return customizationAllowedList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public CustomizationAllowed updateEntityFromDto(CustomizationAllowed customizationAllowed, CustomizationAllowedDto customizationAllowedDto){
        if(customizationAllowedDto.getType()!= null) customizationAllowed.setType(customizationAllowed.getType());
        return customizationAllowed;
    }

    public CustomizationAllowed createEntityFromDto(CustomizationAllowedDto customizationAllowedDto) {
        return new CustomizationAllowed(null, customizationAllowedDto.getType(), null);
    }
}
