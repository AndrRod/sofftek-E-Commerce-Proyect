package com.LicuadoraProyectoEcommerce.dto.mapper;
 
import com.LicuadoraProyectoEcommerce.dto.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.model.EnabledArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnableAreaMapper {

    @Autowired
    private CustomizationAllowedMapper customizationAllowedMapper;
    public EnabledAreaDto getDtoFromEntity(EnabledArea enabledArea){
        return new EnabledAreaDto(enabledArea.getId(), enabledArea.getName());
    }
    public List<EnabledAreaDto> getListDtoFromListEntity(List<EnabledArea> enabledAreaList){
        return enabledAreaList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }

    public EnabledArea updateEntityFrom(EnabledArea enabledArea, EnabledAreaDto enableAreaDto){
        if(enableAreaDto.getName()!=null) enabledArea.setName(enableAreaDto.getName());
        return enabledArea;
    }

    public EnabledArea createEntityFromDto(EnabledAreaDto enableAreaDto) {
        return new EnabledArea(null, enableAreaDto.getName(), new ArrayList<>(), new ArrayList<>());
    }

    public EnabledAreaCompleteDto getCompleteDtoFromEntity(EnabledArea enabledArea){
        return new EnabledAreaCompleteDto(enabledArea.getId(), enabledArea.getName(), customizationAllowedMapper.getListDtoFromListEntity(enabledArea.getCustomizationsAllowed()));
    }
    public List<EnabledAreaCompleteDto> getListCompleteDtoFromListEntity(List<EnabledArea> enabledAreas) {
        return enabledAreas.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
}
