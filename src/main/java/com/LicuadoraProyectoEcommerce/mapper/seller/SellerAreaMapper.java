package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.mapper.manager.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaDto;
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
    public List<SellerAreaDto> getlistDtoFromlistEntity(List<SellerArea> areas){
        return areas.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public SellerAreaCompleteDto getCompleteDtoFromEntity(SellerArea sellerArea){
        return new SellerAreaCompleteDto(sellerArea.getId(), sellerArea.getEnabledArea().getName(), sellerCustomizationMapper.getCompleteDtoListFromEntityList(sellerArea.getCustomizations()));
    }
    public List<SellerAreaCompleteDto> getListCompleteDtoFromEntityList(List<SellerArea>sellerAreas){
        return sellerAreas.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
}
