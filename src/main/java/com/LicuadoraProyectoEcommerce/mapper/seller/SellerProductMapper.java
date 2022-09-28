package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.mapper.manager.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.mapper.manager.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return new SellerProductCompleteDto(sellerProduct.getId(), sellerProduct.getBaseProduct().getName(), sellerProduct.getDescription(), sellerProduct.getFinalPrice(), sellerAreaMapper.getListCompleteDtoFromEntityList(sellerProduct.getAreas()));
    }
    public SellerProductDto getDtoFromEntity(SellerProduct sellerProduct){
        return new SellerProductDto(sellerProduct.getId(), sellerProduct.getBaseProduct().getName(), sellerProduct.getDescription(), sellerProduct.getFinalPrice());
    }
    public List<SellerProductDto> getListDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public List<SellerProductCompleteDto> getListCompleteDtoFromEntityList(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getCompleteDtoFromEntity).collect(Collectors.toList());
    }
    public SellerProduct createEntityFromDto(BaseProduct baseProduct, SellerProductForm sellerProductForm){ //TODO agregar usuario logeado
        return new SellerProduct(sellerProductForm.getBasePrice(), baseProduct, sellerProductForm.getDescription());
    }

    public SellerProduct updateEntityFromDto(SellerProduct sellerProduct, SellerProductForm sellerProductForm) {
        Stream.of(sellerProductForm).forEach(dto->{
            if(dto.getBasePrice()!= null) sellerProduct.setBasePrice(dto.getBasePrice());
            if(dto.getDescription() != null) sellerProduct.setDescription(dto.getDescription());
        });
        return sellerProduct;
    }
}
