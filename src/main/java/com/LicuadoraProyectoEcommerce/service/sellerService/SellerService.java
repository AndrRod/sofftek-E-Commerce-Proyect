package com.LicuadoraProyectoEcommerce.service.sellerService;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerDto;
import java.util.List;
import java.util.Map;

public interface SellerService {
    SellerDto getById(Long id);
    Map<String, String> deleteById(Long id);
    List<SellerDto> getListEntityPage(Integer page);
}
