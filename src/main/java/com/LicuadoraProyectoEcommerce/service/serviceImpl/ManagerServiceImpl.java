package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.repository.ManagerRepository;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }
}
