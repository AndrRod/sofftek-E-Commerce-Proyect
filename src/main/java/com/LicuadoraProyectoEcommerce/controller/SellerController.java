package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.model.Seller;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @GetMapping
    public ResponseEntity<List<SellerDto>> findPaginationListSeller(@RequestParam Integer page){
        return ResponseEntity.ok(sellerService.findAll(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(sellerService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.status(200).body(sellerService.deleteSeller(id));
    }
}
