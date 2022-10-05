package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @GetMapping("/{id}/publications")
    public ResponseEntity<SellerStoreCompleteDto> findEntityById(@PathVariable String id, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false) String state){
        return ResponseEntity.ok(storeService.findById(Long.valueOf(id), page, state));
    }
    @GetMapping
    public ResponseEntity<List<SellerStoreDto>> getListStorePagination(@RequestParam String page){
        return  ResponseEntity.ok(storeService.listDtoPagination(Integer.valueOf(page)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(storeService.deleteById(Long.valueOf(id), request));
    }
    @PostMapping
    public ResponseEntity<SellerStoreCompleteDto> createEntity(@RequestBody @Valid SellerStoreDto sellerStoreDto, HttpServletRequest request){
        return ResponseEntity.status(201).body(storeService.createEntity(sellerStoreDto, request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SellerStoreCompleteDto> updateEntity(@PathVariable String id, @RequestBody SellerStoreDto sellerStoreDto, HttpServletRequest request){
        return ResponseEntity.ok(storeService.updateEntity(Long.valueOf(id),sellerStoreDto, request));
    }
    @PostMapping("/{id}/payment")
    public ResponseEntity<SellerStoreCompleteDto> addNewPaymentMethod(@PathVariable String id, @RequestParam String method, HttpServletRequest request){
        return ResponseEntity.status(201).body(storeService.addNewPaymentMethod(Long.valueOf(id), method,request));
    }
    @DeleteMapping("/{id}/payment")
    public ResponseEntity<SellerStoreCompleteDto> removePaymentMethod(@PathVariable String id, @RequestParam String method, HttpServletRequest request){
        return ResponseEntity.status(200).body(storeService.removePaymentMethod(Long.valueOf(id), method, request));
    }

}
