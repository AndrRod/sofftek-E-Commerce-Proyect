package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;


}
