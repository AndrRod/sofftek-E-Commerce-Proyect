package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BaseProductMapper {
    @Autowired
    private EnableAreaMapper enableAreaMapper;
    public BaseProductDtoComplete getDtoFromEntity(BaseProduct baseProduct){return new BaseProductDtoComplete(baseProduct.getId(), baseProduct.getName(), baseProduct.getPrice(), baseProduct.getDescription(), baseProduct.getDaysToManufacture(), baseProduct.getManager().getUser().getName(), enableAreaMapper.getListDtoFromListEntity(baseProduct.getEnabledAreas()));}
    public List<BaseProductDtoComplete> getListDtoCompleteFromListEntity(List<BaseProduct> baseProducts){return baseProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public BaseProduct getEntityCreateFromDto(BaseProductDto baseProductDto){
        return new BaseProduct(null, baseProductDto.getName(), baseProductDto.getPrice(), baseProductDto.getDescription(), baseProductDto.getDaysToManufacture(), null, new ArrayList<>()); //TODO
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
