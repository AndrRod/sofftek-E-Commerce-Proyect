package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerAreaMapper {
    @Autowired
    private EnableAreaMapper enableAreaMapper;
    @Autowired
    private SellerCustomizationMapper sellerCustomizationMapper;

    public SellerAreaDto getDtoFromEntity(SellerArea sellerArea){
        return new SellerAreaDto(sellerArea.getId(), enableAreaMapper.getDtoFromEntity(sellerArea.getEnabledArea()));
        }
    public List<SellerAreaDto> listDtoFromlistEntity(List<SellerArea> areas){
        return areas.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public SellerAreaCompleteDto getCompleteDtoFromEntity(SellerArea sellerArea){
        return new SellerAreaCompleteDto(sellerArea.getId(), sellerArea.getEnabledArea().getName(), sellerCustomizationMapper.getDtoListFromEntityList(sellerArea.getCustomizations()));
    }
    public List<SellerAreaCompleteDto> getListCompleteDtoFromEntityList(List<SellerArea>sellerAreas){
        return sellerAreas.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
}