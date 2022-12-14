package com.LicuadoraProyectoEcommerce.mapper.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
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
        if(customizationAllowedDto.getType()!= null) customizationAllowed.setType(customizationAllowedDto.getType());
        return customizationAllowed;
    }

    public CustomizationAllowed createEntityFromDto(CustomizationAllowedDto customizationAllowedDto) {
        return new CustomizationAllowed(customizationAllowedDto.getType());
    }
}
