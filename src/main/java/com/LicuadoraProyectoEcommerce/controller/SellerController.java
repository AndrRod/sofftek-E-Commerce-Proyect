package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.ManagerDto;
import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @GetMapping("/{id}")
    ResponseEntity<SellerDto> getEntityById(@PathVariable String id){
        return ResponseEntity.ok(sellerService.getById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<SellerDto>> getEntityListPage(@RequestParam String page){
        return ResponseEntity.ok(sellerService.getListEntityPage(Integer.valueOf(page)));
    }

}
