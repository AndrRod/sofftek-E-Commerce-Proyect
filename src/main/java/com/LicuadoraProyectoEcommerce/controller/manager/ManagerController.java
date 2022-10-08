package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.service.managerService.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Manager")
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Operation(summary = "find manager by id")
    @GetMapping("/{id}")
    ResponseEntity<ManagerDto> getEntityById(@Parameter(description = "insert manager id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(managerService.getById(Long.valueOf(id)));
    }
    @Operation(summary = "get 10 manager list by page")
    @GetMapping
    ResponseEntity<List<ManagerDto>> getEntityListPage(@Parameter(description = "insert a page number", example = "0") @RequestParam String page){
        return ResponseEntity.ok(managerService.getListEntityPage(Integer.valueOf(page)));
    }
}
