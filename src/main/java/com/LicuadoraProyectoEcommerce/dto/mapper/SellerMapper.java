package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerMapper {
    public SellerDto getDtoFromEntity(Seller seller){
        return new SellerDto(seller.getId(), seller.getUser().getName(), seller.getUser().getEmail());
    }
    public List<SellerDto> listDtoFromListEntities(List<Seller> sellers){
        return sellers.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }

}
