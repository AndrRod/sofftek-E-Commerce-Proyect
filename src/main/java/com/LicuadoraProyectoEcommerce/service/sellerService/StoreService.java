package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.model.seller.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface StoreService {
    SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request);
    SellerStoreCompleteDto updateEntity(Long idStore, SellerStoreDto sellerStoreDto, HttpServletRequest request);
    SellerStoreCompleteDto findById(Long id, Integer pagePublications, String state);
    Store findEntityById(Long id);
    Map<String, String> deleteById(Long id, HttpServletRequest request);
    SellerStoreCompleteDto addNewPaymentMethod(Long id, String newPayMethod, HttpServletRequest request);
    SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod, HttpServletRequest request);
    List<SellerStoreDto> listDtoPagination(Integer page);
}
