package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerProductMapper {
    @Autowired
    private SellerAreaMapper sellerAreaMapper;
    @Autowired
    private BaseProductMapper baseProductMapper;
    public SellerProductDto getDtoFromEntity(SellerProduct sellerProduct){
        return new SellerProductDto(sellerProduct.getId(), baseProductMapper.getDtoFromEntity(sellerProduct.getBaseProduct()), sellerAreaMapper.listDtoFromlistEntity(sellerProduct.getAreas()));
    }
    public List<SellerProductDto> getListDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}
