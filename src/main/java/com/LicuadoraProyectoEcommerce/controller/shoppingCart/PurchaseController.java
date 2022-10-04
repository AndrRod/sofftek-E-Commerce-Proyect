package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(purchaseService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(purchaseService.deleteById(Long.valueOf(id)));
    }
    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(purchaseService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping("/cart/{idShoppingCart}")
    public ResponseEntity<PurchaseDto> createEntity(@PathVariable String idShoppingCart, @RequestBody @Valid PaymentForm paymentForm){
        return new ResponseEntity<>(purchaseService.createEntity(Long.valueOf(idShoppingCart), paymentForm), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/cart/{idShoppingCart}")
    public ResponseEntity<PurchaseDto> updateEntity(@PathVariable String id, @PathVariable(required = false) Long idShoppingCart, @RequestBody PaymentForm paymentForm){
        return ResponseEntity.ok(purchaseService.updateEntity(Long.valueOf(id), idShoppingCart, paymentForm));
    }
}
