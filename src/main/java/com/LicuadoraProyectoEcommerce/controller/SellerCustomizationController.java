package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sellercustomization")
public class SellerCustomizationController {
    @Autowired
    private SellerCustomizationService sellerCustomizationService;
    @GetMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> findById(@PathVariable String id){
        return ResponseEntity.ok(sellerCustomizationService.findById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<SellerCustomizationCompleteDto>> getListPagination(@RequestParam String page){
        return ResponseEntity.ok(sellerCustomizationService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> updateEntity(@PathVariable String id, @RequestBody SellerCustomizationDto customizationDto){
        return ResponseEntity.ok(sellerCustomizationService.updateEntity(Long.valueOf(id), customizationDto));
    }
    @DeleteMapping("params/{id}")
    ResponseEntity<Map<String, String>> deleteEntityParams(@PathVariable String id){
        return ResponseEntity.ok(sellerCustomizationService.deleteEntityParams(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteEntityById(@PathVariable String id){ //TODO AGREGADO PARA PRUEBA
        return ResponseEntity.ok(sellerCustomizationService.deleteEntity(Long.valueOf(id)));
    }
}
