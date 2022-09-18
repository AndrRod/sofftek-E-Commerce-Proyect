package com.LicuadoraProyectoEcommerce.service;


import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;

import java.util.List;
import java.util.Map;

public interface SellerProductService {
    SellerProduct findEntityById(Long id);
    SellerProductCompleteDto findById(Long id);
    List<SellerProductCompleteDto> listDtoPagination(Integer page);
    SellerProductCompleteDto createEntity(BaseProduct baseProduct);
    Map<String, String> deleteById(Long id);
    SellerProductCompleteDto updateEntity(Long id, SellerProductCompleteDto sellerProductDto);
}
