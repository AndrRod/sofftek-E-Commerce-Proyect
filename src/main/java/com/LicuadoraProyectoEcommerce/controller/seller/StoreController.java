package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @GetMapping("/{id}")
    public ResponseEntity<SellerStoreCompleteDto> findEntityById(@PathVariable String id){
    return ResponseEntity.ok(storeService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(storeService.deleteById(Long.valueOf(id)));
    }
    @PostMapping
    public ResponseEntity<SellerStoreCompleteDto> createEntity(@RequestBody @Valid SellerStoreDto sellerStoreDto, HttpServletRequest request){
        return ResponseEntity.status(201).body(storeService.createEntity(sellerStoreDto, request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SellerStoreCompleteDto> updateEntity(@PathVariable String id, SellerStoreDto sellerStoreDto){
        return ResponseEntity.ok(storeService.updateEntity(Long.valueOf(id), sellerStoreDto));
    }
    @PostMapping("/{id}/add")
    public ResponseEntity<SellerStoreCompleteDto> addNewPaymentMethod(@PathVariable String id, @RequestParam String paymethod){
        return ResponseEntity.status(201).body(storeService.addNewPaymentMethod(Long.valueOf(id), paymethod));
    }
    @PostMapping("/{id}/remove")
    public ResponseEntity<SellerStoreCompleteDto> removePaymentMethod(@PathVariable String id, @RequestParam String paymethod){
        return ResponseEntity.status(201).body(storeService.removePaymentMethod(Long.valueOf(id), paymethod));
    }
}
