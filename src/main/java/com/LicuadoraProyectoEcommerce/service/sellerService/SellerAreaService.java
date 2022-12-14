package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;

import java.util.List;

public interface SellerAreaService {
    SellerArea createAndUpdateEntity(SellerArea sellerArea);
    SellerAreaDto findById(Long id);
    SellerArea findEntityById(Long id);
    List<SellerAreaCompleteDto> geDtoListPagination(Integer page);
    void delete(SellerArea area);
}