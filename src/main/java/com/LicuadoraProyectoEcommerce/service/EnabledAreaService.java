package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.model.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.EnabledArea;

import java.util.List;
import java.util.Map;

public interface EnabledAreaService {

    EnabledAreaDto createEntity(EnabledAreaDto enabledAreaDto);

    EnabledAreaDto findById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<EnabledAreaCompleteDto> findDtoListPagination(Integer page);
    EnabledAreaDto updateEntity(Long id, EnabledAreaDto enableAreaDto);
    EnabledArea findByName(String name);
    EnabledAreaCompleteDto addCustomizationAllowedToEntity(Long id, CustomizationAllowed customizationAllowed);
    EnabledAreaCompleteDto removeCustomizationAllowedToEntity(Long id,  CustomizationAllowed customizationAllowed);
    EnabledArea findEntityById(Long id);
}
