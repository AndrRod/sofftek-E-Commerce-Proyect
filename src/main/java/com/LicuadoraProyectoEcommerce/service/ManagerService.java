package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.ManagerDto;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    ManagerDto  getById(Long id);
    Map<String, String> deleteById(Long id);
    List<ManagerDto> getListEntityPage(Integer page);
}
