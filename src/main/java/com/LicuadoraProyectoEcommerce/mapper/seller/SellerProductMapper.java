package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.mapper.manager.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.mapper.manager.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductPriceForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
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
    private EnableAreaMapper enableAreaMapper;
    @Autowired
    private BaseProductMapper baseProductMapper;
    @Autowired
    private SellerAreaMapper sellerAreaMapper;
    public SellerProductCompleteDto getCompleteDtoFromEntity(SellerProduct sellerProduct){
        return new SellerProductCompleteDto(sellerProduct.getId(), sellerProduct.getBaseProduct().getName(), sellerProduct.getBaseProduct().getDescription(), sellerProduct.getBasePrice(), sellerProduct.getFinalPrice(), sellerAreaMapper.getListCompleteDtoFromEntityList(sellerProduct.getAreas()));
    }
    public SellerProductDto getDtoFromEntity(SellerProduct sellerProduct){
        return new SellerProductDto(sellerProduct.getId(), sellerProduct.getBaseProduct().getName(), sellerProduct.getBaseProduct().getDescription(), sellerProduct.getBasePrice());
    }
    public List<SellerProductDto> getListDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public List<SellerProductCompleteDto> getListCompleteDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
    public SellerProduct createEntityFromDto(BaseProduct baseProduct, SellerProductPriceForm sellerProductPriceForm){ //TODO agregar usuario logeado
        return new SellerProduct(sellerProductPriceForm.getBasePrice(), baseProduct);
    }
}
