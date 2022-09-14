package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.ManagerDto;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.model.User;


public interface ManagerService {
    ManagerDto createEntity(String email);
    ManagerDto  getById(Long id);
    void deleteById(Long id);
}
