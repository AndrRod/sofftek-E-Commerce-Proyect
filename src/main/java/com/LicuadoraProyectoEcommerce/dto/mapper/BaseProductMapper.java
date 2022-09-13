package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import com.LicuadoraProyectoEcommerce.service.serviceImpl.UserLoged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BaseProductMapper {
    @Autowired
    private UserLoged userLoged;
    public BaseProductDtoComplete getDtoFromEntity(BaseProduct baseProduct){return new BaseProductDtoComplete(baseProduct.getName(), baseProduct.getPrice(), baseProduct.getDescription(), baseProduct.getDaysToManufacture(), baseProduct.getManager().getName());}
    public List<BaseProductDtoComplete> getListDtoFromListEntity(List<BaseProduct> baseProducts){return baseProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public BaseProduct getEntityCreateFromDto(BaseProductDto baseProductDto){
        return new BaseProduct(null, baseProductDto.getName(), baseProductDto.getPrice(), baseProductDto.getDescription(), baseProductDto.getDaysToManufacture(), userLoged.findManagerByEmail("andresrod@gmail.com"), new ArrayList<>()); //TODO
    }
    public BaseProduct getEntityUpdateFromDto(BaseProduct baseProduct, BaseProductDto baseProductDto){
        Stream.of(baseProductDto).forEach((dto)-> {
            if (dto.getName() != null) baseProduct.setName(dto.getName());
            if (dto.getDescription() != null) baseProduct.setDescription(dto.getDescription());
            if (dto.getPrice() != null) baseProduct.setPrice(dto.getPrice());
            if (dto.getDaysToManufacture() != null) baseProduct.setDaysToManufacture(dto.getDaysToManufacture());
        });
        return baseProduct;
    }
}
