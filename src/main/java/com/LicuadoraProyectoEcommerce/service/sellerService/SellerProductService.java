package com.LicuadoraProyectoEcommerce.service.sellerService;


import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductPriceForm;
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
    SellerProductDto createEntity(BaseProduct baseProduct, SellerProductPriceForm sellerProductPriceForm, HttpServletRequest request);
    void deleteByEntity(SellerProduct sellerProduct);
}
