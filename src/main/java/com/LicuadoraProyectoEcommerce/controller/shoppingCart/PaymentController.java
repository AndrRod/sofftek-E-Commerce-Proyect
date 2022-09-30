package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PaymentDto;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(paymentService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(paymentService.deleteById(Long.valueOf(id)));
    }
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(paymentService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping("/cart/{idShoppingCart}")
    public ResponseEntity<PaymentDto> createEntity(@PathVariable String idShoppingCart, @RequestBody @Valid PaymentForm paymentForm){
        return new ResponseEntity<>(paymentService.createEntity(Long.valueOf(idShoppingCart), paymentForm), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/cart/{idShoppingCart}")
    public ResponseEntity<PaymentDto> updateEntity(@PathVariable String id, @PathVariable(required = false) Long idShoppingCart, @RequestBody PaymentForm paymentForm){
        return ResponseEntity.ok(paymentService.updateEntity(Long.valueOf(id), idShoppingCart, paymentForm));
    }
}
