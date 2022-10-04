package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.model.seller.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface StoreService {
    SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request);
    SellerStoreCompleteDto updateEntity(Long id, SellerStoreDto sellerStoreDto);
    SellerStoreCompleteDto findById(Long id, Integer pagePruductList);
    Store findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    SellerStoreCompleteDto addNewPaymentMethod(Long id, String newPayMethod);
    SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod);
    List<SellerStoreDto> listDtoPagination(Integer page);
}
