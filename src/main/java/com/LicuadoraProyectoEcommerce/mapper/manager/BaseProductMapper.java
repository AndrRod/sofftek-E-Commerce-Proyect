package com.LicuadoraProyectoEcommerce.mapper.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
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
    public BaseProductDtoComplete getDtoFromEntity(BaseProduct baseProduct){return new BaseProductDtoComplete(baseProduct.getId(), baseProduct.getName(), baseProduct.getPrice(), baseProduct.getDaysToManufacture(), baseProduct.getManagerCreator().getUser().getName(), enableAreaMapper.getListCompleteDtoFromListEntity(baseProduct.getEnabledAreas()), baseProduct.getCreationDate(), baseProduct.getUpdateDate(), baseProduct.getManagerLastUpdate());}
    public List<BaseProductDtoComplete> getListDtoCompleteFromListEntity(List<BaseProduct> baseProducts){return baseProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public BaseProduct getEntityCreateFromDto(BaseProductDto baseProductDto){
        return new BaseProduct(baseProductDto.getName(), baseProductDto.getPrice(), baseProductDto.getDaysToManufacture(), new ArrayList<>());
    }
    public BaseProduct getEntityUpdateFromDto(BaseProduct baseProduct, BaseProductDto baseProductDto){
        Stream.of(baseProductDto).forEach((dto)-> {
            if (dto.getName() != null) baseProduct.setName(dto.getName());
            if (dto.getPrice() != null) baseProduct.setPrice(dto.getPrice());
            if (dto.getDaysToManufacture() != null) baseProduct.setDaysToManufacture(dto.getDaysToManufacture());
        });
        return baseProduct;
    }
}
