package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.service.managerService.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/{id}")
    ResponseEntity<ManagerDto> getEntityById(@PathVariable String id){
        return ResponseEntity.ok(managerService.getById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<ManagerDto>> getEntityListPage(@RequestParam String page){
        return ResponseEntity.ok(managerService.getListEntityPage(Integer.valueOf(page)));
    }
}
