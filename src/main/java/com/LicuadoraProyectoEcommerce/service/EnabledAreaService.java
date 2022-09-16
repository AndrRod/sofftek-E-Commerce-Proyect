package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.EnableAreaDto;

import java.util.List;
import java.util.Map;

public interface EnabledAreaService {
    EnableAreaDto createEntity(EnableAreaDto enableAreaDto);
    EnableAreaDto findById(Long id);
    Map<String, String> deleteEntityById(Long id);
    List<EnableAreaDto> findDtoListPagination(Integer page);
    EnableAreaDto updateEntity(Long id, EnableAreaDto enableAreaDto);
}
