package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.form.PublicationForm;
import com.LicuadoraProyectoEcommerce.model.seller.Publication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PublicationService {
    PublicationDto createEntity(Long idSellerProduct, PublicationForm publicationForm, HttpServletRequest request);
    PublicationDto updateEntity(Long id, Long idSellerProduct, PublicationForm publicationForm, HttpServletRequest request);
    Map<String, String> deleteById(Long id, HttpServletRequest request);
    List<PublicationDto> getListEntityPage(Integer page, String state);
    Publication findEntityById(Long id);
    PublicationDto findById(Long id);
}
