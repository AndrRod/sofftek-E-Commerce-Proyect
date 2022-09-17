package com.LicuadoraProyectoEcommerce.service;


import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;

import java.util.List;
import java.util.Map;

public interface SellerProductService {
    SellerProduct findEntityById(Long id);
    SellerProductDto findById(Long id);
    List<SellerProductDto> listDtoPagination(Integer page);
    SellerProductDto createEntity(SellerProductDto sellerProductDto);
    Map<String, String> deleteById(Long id);
    SellerProductDto updateEntity(Long id, SellerProductDto sellerProductDto);
}
