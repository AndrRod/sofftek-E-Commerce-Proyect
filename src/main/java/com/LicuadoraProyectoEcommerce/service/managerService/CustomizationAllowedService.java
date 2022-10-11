package com.LicuadoraProyectoEcommerce.service.managerService;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;

import java.util.List;
import java.util.Map;

public interface CustomizationAllowedService {
    CustomizationAllowedDto createEntity(CustomizationAllowedDto customizationAllowedDto);
    CustomizationAllowedDto findDtoById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<CustomizationAllowedDto> findDtoListPagination(Integer page);
    CustomizationAllowedDto updateEntity(Long id, CustomizationAllowedDto customizationAllowedDto);
    CustomizationAllowed findByType(String type);
    CustomizationAllowed findEntityById(Long id);
}
