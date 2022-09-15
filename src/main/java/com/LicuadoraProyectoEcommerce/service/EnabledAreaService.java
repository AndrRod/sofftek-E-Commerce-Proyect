package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.EnableAreaDto;

import java.util.List;
import java.util.Map;

public interface EnabledAreaService {
    EnableAreaDto createEntity(EnableAreaDto enableAreaDto);
    EnableAreaDto findEntityById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<EnableAreaDto> findEntityListPagination(Integer page);
    EnableAreaDto updateEntity(Long id, EnableAreaDto enableAreaDto);
}
