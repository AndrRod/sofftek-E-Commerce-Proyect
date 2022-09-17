package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.service.CustomizationAllowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customizationAllowed")
public class CustomizationAllowedController {
    @Autowired
    private CustomizationAllowedService customizationAllowedService;
    @GetMapping("/{id}")
    ResponseEntity<CustomizationAllowedDto> getEntityById(@PathVariable String id){
        return ResponseEntity.ok(customizationAllowedService.findDtoById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<CustomizationAllowedDto>> getListEntityPagination(@RequestParam String page){
        return ResponseEntity.ok(customizationAllowedService.findDtoListPagination(Integer.valueOf(page)));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(customizationAllowedService.deleteEntityById(Long.valueOf(id)));
    }
    @PostMapping
    ResponseEntity<CustomizationAllowedDto> createentity(@RequestBody CustomizationAllowedDto customizationAllowedDto){
        return ResponseEntity.ok(customizationAllowedService.createEntity(customizationAllowedDto));
    }
}
