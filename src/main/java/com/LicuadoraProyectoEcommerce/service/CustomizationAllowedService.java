package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.CustomizationAllowedDto;

import java.util.List;
import java.util.Map;

public interface CustomizationAllowedService {
    CustomizationAllowedDto createEntity(CustomizationAllowedDto customizationAllowedDto);
    CustomizationAllowedDto findEntityById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<CustomizationAllowedDto> findEntityListPagination(Integer page);
    CustomizationAllowedDto updateEntity(Long id, CustomizationAllowedDto customizationAllowedDto);
}
