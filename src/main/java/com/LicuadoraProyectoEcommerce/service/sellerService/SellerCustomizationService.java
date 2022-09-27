package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;

import java.util.List;
import java.util.Map;

public interface SellerCustomizationService {
    SellerCustomizationCompleteDto findById(Long id);
    SellerCustomization findEntityById(Long id);
    List<SellerCustomizationCompleteDto> geDtoListPagination(Integer page);
    SellerCustomizationCompleteDto updateEntity(Long idCustomization, SellerCustomizationDto sellerCustomizationForm);
    Map<String, String> deleteEntityParams(Long idCustomization);
    Map<String, String> deleteEntity(Long idCustomization);
}
