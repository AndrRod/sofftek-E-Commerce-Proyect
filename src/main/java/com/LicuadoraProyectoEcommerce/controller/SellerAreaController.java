package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellerArea")
public class SellerAreaController {
    @Autowired
    private SellerAreaService sellerAreaService;
    @GetMapping
    public ResponseEntity<List<SellerAreaCompleteDto>> getListDtoPagiantion(@RequestParam String page){
        return ResponseEntity.ok(sellerAreaService.geDtoListPagination(Integer.valueOf(page)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerAreaDto> findById(@PathVariable String id){
        return ResponseEntity.ok(sellerAreaService.findById(Long.valueOf(id)));
    }
}
