package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.model.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> findAll();
    Manager createManager(Manager manager);
}
