package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SellerCustomizationService {
    SellerCustomizationCompleteDto findById(Long id);
    SellerCustomization findEntityById(Long id);
    List<SellerCustomizationCompleteDto> geDtoListPagination(Integer page);
    SellerCustomizationCompleteDto updateEntity(Long idCustomization, SellerCustomizationDto sellerCustomizationForm, HttpServletRequest request);
    Map<String, String> deleteEntityParams(Long idCustomization, HttpServletRequest request);
    Map<String, String> deleteEntity(Long idCustomization, HttpServletRequest request);
}
