package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.form.SellerCustomizationNameFrom;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;

import java.util.List;

public interface SellerCustomizationService {
    SellerCustomization createAndUpdateEntity(SellerCustomization sellerCustomization, SellerCustomizationNameFrom NameForm);
    SellerCustomizationCompleteDto findById(Long id);
    SellerCustomization findEntityById(Long id);
    List<SellerCustomizationCompleteDto> geDtoListPagination(Integer page);
    void delete(SellerCustomization sellerCustomization);
}
