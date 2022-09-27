package com.LicuadoraProyectoEcommerce.service.managerService;

import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    ManagerDto  getById(Long id);
    Map<String, String> deleteById(Long id);
    List<ManagerDto> getListEntityPage(Integer page);
}
