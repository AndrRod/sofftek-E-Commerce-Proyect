package com.LicuadoraProyectoEcommerce.service.sellerService;


import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SellerProductService {
    SellerProduct findEntityById(Long id);
    SellerProductCompleteDto findById(Long id);
    List<SellerProductCompleteDto> listDtoPagination(Integer page);
    Map<String, String> deleteById(Long id);
    SellerProductDto createEntity(BaseProduct baseProduct, SellerProductForm sellerProductForm, HttpServletRequest request);
    SellerProductDto updateEntity(Long id, SellerProductForm sellerProductForm);
    void deleteByEntity(SellerProduct sellerProduct);
    SellerProductDto addStoreById(Long idProduct);
    SellerProductDto removeStoreById(Long idProduct);

    List<SellerProductDto> listPartDtoPagination(Integer page);
}
