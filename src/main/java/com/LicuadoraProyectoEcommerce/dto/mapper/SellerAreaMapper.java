package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerAreaMapper {
    @Autowired
    private SellerCustomizationMapper customizationMapper;
    public SellerAreaDto getDtoFromEntity(SellerArea sellerArea){
        return new SellerAreaDto(sellerArea.getName(), customizationMapper.getDtoListFromEntityList(sellerArea.getCustomizations()));
        }
    List<SellerAreaDto> listDtoFromlistEntity(List<SellerArea> areas){
        return areas.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}