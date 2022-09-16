package com.LicuadoraProyectoEcommerce.dto.mapper;
 
import com.LicuadoraProyectoEcommerce.dto.EnableAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.EnableAreaDto;
import com.LicuadoraProyectoEcommerce.model.EnabledArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EnableAreaMapper {
    @Autowired
    private BaseProductMapper baseProductMapper;
    @Autowired
    private CustomizationAllowedMapper customizationAllowedMapper;
    public EnableAreaDto getDtoFromEntity(EnabledArea enabledArea){
        return new EnableAreaDto(enabledArea.getName());
    }
    public List<EnableAreaDto> getListDtoFromListEntity(List<EnabledArea> enabledAreaList){
        return enabledAreaList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public EnableAreaCompleteDto getDtoCompeteFromEntity(EnabledArea enabledArea){
        return new EnableAreaCompleteDto(enabledArea.getName(), baseProductMapper.getListDtoCompleteFromListEntity(enabledArea.getBaseProducts()), customizationAllowedMapper.getListDtoFromListEntity(enabledArea.getCustomizationsAllowed()));
    }
    public EnabledArea updateEntityFrom(EnabledArea enabledArea, EnableAreaDto enableAreaDto){
        if(enableAreaDto.getName()!=null) enabledArea.setName(enableAreaDto.getName());
        return enabledArea;
    }

    public EnabledArea createEntityFromDto(EnableAreaDto enableAreaDto) {
        return new EnabledArea(null, enableAreaDto.getName(), null, null);
    }
}
