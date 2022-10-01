package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(invoiceService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(invoiceService.deleteById(Long.valueOf(id)));
    }
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(invoiceService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping("/payment/{idPayment}")
    public ResponseEntity<InvoiceDto> createEntity(@PathVariable String idPayment, @RequestBody @Valid InvoiceForm invoiceForm){
        return ResponseEntity.status(201).body(invoiceService.createEntity(Long.valueOf(idPayment), invoiceForm));
    }
    @PutMapping("/{id}/payment/{idPayment}")
    public ResponseEntity<InvoiceDto> updateEntity(@PathVariable String id, @PathVariable(required = false) Long idPayment, @RequestBody @Valid InvoiceForm invoiceForm){
        return ResponseEntity.ok(invoiceService.updateEntity(Long.valueOf(id), idPayment, invoiceForm));
    }
}
