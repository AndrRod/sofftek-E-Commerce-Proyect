package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface StoreService {
    SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request);
    SellerStoreCompleteDto updateEntity(Long id, SellerStoreDto sellerStoreDto);
    SellerStoreCompleteDto findById(Long id);
    Store findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    SellerStoreCompleteDto addNewPaymentMethod(Long id, String newPayMethod);
    SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod);
    SellerStoreCompleteDto addProductById(Long idStore, Long idProduct);
    SellerStoreCompleteDto removeProductById(Long idStore, Long idProduct);
}
