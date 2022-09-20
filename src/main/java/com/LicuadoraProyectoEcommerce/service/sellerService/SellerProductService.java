package com.LicuadoraProyectoEcommerce.service.sellerService;


import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductPriceForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;

import java.util.List;
import java.util.Map;

public interface SellerProductService {
    SellerProduct findEntityById(Long id);
    SellerProductCompleteDto findById(Long id);
    List<SellerProductCompleteDto> listDtoPagination(Integer page);
    Map<String, String> deleteById(Long id);
    SellerProductDto createAndUpdateEntity(BaseProduct baseProduct, SellerProductPriceForm sellerProductPriceForm);
    void deleteByEntity(SellerProduct sellerProduct);
}
