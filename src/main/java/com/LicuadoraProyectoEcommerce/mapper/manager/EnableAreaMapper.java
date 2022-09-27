package com.LicuadoraProyectoEcommerce.mapper.manager;
 
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.mapper.seller.SellerCustomizationMapper;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnableAreaMapper {

    @Autowired
    private CustomizationAllowedMapper customizationAllowedMapper;
    @Autowired
    private SellerCustomizationMapper customizationMapper;
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
        return new EnabledArea(enableAreaDto.getName());
    }

    public EnabledAreaCompleteDto getCompleteDtoFromEntity(EnabledArea enabledArea){
        return new EnabledAreaCompleteDto(enabledArea.getId(), enabledArea.getName(), customizationAllowedMapper.getListDtoFromListEntity(enabledArea.getCustomizationsAllowed()));
    }
    public List<EnabledAreaCompleteDto> getListCompleteDtoFromListEntity(List<EnabledArea> enabledAreas) {
        return enabledAreas.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }

    public List<SellerAreaCompleteDto> getListSellerCompleteDtoFromListEntity(List<EnabledArea> areas) {
        return areas.stream().map(this::getSellerCompleteDtoFromEntity).collect(Collectors.toList());
    }

    private SellerAreaCompleteDto getSellerCompleteDtoFromEntity(EnabledArea enabledArea) {
        return new SellerAreaCompleteDto(enabledArea.getId(), enabledArea.getName(), customizationMapper.getCompleteDtoListFromEntityList(enabledArea.getCustomizations()));
    }
}
