package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.BaseProductBasicDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
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
    public BaseProductDtoComplete getDtoFromEntity(BaseProduct baseProduct){return new BaseProductDtoComplete(baseProduct.getId(), baseProduct.getName(), baseProduct.getEstimatedPrice(), baseProduct.getDescription(), baseProduct.getDaysToManufacture(), baseProduct.getManager().getUser().getName(), enableAreaMapper.getListCompleteDtoFromListEntity(baseProduct.getEnabledAreas()));}
    public List<BaseProductDtoComplete> getListDtoCompleteFromListEntity(List<BaseProduct> baseProducts){return baseProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public BaseProduct getEntityCreateFromDto(BaseProductDto baseProductDto){
        return new BaseProduct(baseProductDto.getName(), baseProductDto.getEstimatedPrice(), baseProductDto.getDescription(), baseProductDto.getDaysToManufacture(), new ArrayList<>()); //TODO se crea producto agregar manager logeado
    }
    public BaseProduct getEntityUpdateFromDto(BaseProduct baseProduct, BaseProductDto baseProductDto){
        Stream.of(baseProductDto).forEach((dto)-> {
            if (dto.getName() != null) baseProduct.setName(dto.getName());
            if (dto.getDescription() != null) baseProduct.setDescription(dto.getDescription());
            if (dto.getEstimatedPrice() != null) baseProduct.setEstimatedPrice(dto.getEstimatedPrice());
            if (dto.getDaysToManufacture() != null) baseProduct.setDaysToManufacture(dto.getDaysToManufacture());
        });
        return baseProduct;
    }

    public BaseProductBasicDto getBaseDtoFromEntity(BaseProduct baseProduct) {
        return new BaseProductBasicDto(baseProduct.getName(), baseProduct.getDescription());
    }
}
