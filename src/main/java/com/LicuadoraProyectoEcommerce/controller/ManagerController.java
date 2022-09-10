package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Manager>> findAllManager(){
        return ResponseEntity.ok(managerService.findAll());
    }

    @PostMapping
    private ResponseEntity<Manager> createManager(@RequestBody Manager manager){
        return ResponseEntity.status(201).body(managerService.createManager(manager));
    }
}
