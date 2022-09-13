package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.SellerCompleteDto;
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
    @GetMapping
    public ResponseEntity<List<SellerDto>> findPaginationListSeller(@RequestParam String page){
        return ResponseEntity.ok(sellerService.findAll(Integer.valueOf(page)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> findById(@PathVariable String id){
        return ResponseEntity.status(200).body(sellerService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.status(200).body(sellerService.deleteSeller(Long.valueOf(id)));
    }
    @PutMapping("{id}")
    public ResponseEntity<SellerDto> udpateDto(@PathVariable String id, @RequestBody SellerCompleteDto sellerCompleteDto){
        return ResponseEntity.status(200).body(sellerService.updateSeller(Long.valueOf(id), sellerCompleteDto));
    }
}
