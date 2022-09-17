package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.model.CustomizationAllowed;

import java.util.List;
import java.util.Map;

public interface CustomizationAllowedService {
    CustomizationAllowedDto createEntity(CustomizationAllowedDto customizationAllowedDto);
    CustomizationAllowedDto findDtoById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<CustomizationAllowedDto> findDtoListPagination(Integer page);
    CustomizationAllowedDto updateEntity(Long id, CustomizationAllowedDto customizationAllowedDto);
    CustomizationAllowed findByTypeAndName(String type);
}
