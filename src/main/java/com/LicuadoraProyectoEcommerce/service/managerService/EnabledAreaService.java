package com.LicuadoraProyectoEcommerce.service.managerService;

import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;

import java.util.List;
import java.util.Map;

public interface EnabledAreaService {

    EnabledAreaDto createEntity(EnabledAreaDto enabledAreaDto);

    EnabledAreaCompleteDto findById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<EnabledAreaCompleteDto> findDtoListPagination(Integer page);
    EnabledAreaDto updateEntity(Long id, EnabledAreaDto enableAreaDto);
    EnabledArea findByName(String name);
    EnabledAreaCompleteDto addCustomizationAllowedToEntity(Long id, CustomizationAllowed customizationAllowed);
    EnabledAreaCompleteDto removeCustomizationAllowedToEntity(Long id,  CustomizationAllowed customizationAllowed);
    EnabledArea findEntityById(Long id);
}
