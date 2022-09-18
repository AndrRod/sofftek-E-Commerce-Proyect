package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerProductMapper {
    @Autowired
    private SellerRepository repository;
    @Autowired
    private SellerAreaMapper sellerAreaMapper;
    @Autowired
    private BaseProductMapper baseProductMapper;
    public SellerProductCompleteDto getDtoFromEntity(SellerProduct sellerProduct){
        return new SellerProductCompleteDto(sellerProduct.getId(), baseProductMapper.getDtoFromEntity(sellerProduct.getBaseProduct()), sellerProduct.getBasePrice(), sellerProduct.getFinalPrice(), sellerAreaMapper.listDtoFromlistEntity(sellerProduct.getAreas()));
    }
    public List<SellerProductCompleteDto> getListDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public SellerProduct createEntityFromDto(BaseProduct baseProduct, SellerProductDto sellerProductDto){
        return new SellerProduct(sellerProductDto.getBasePrice(), baseProduct, repository.findById(2L).get()); //TODO
    }
}
