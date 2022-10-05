package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private BaseProductService baseProductService;
    @Autowired
    private UserAuthService userAuthService;
    @PostMapping("/{id}")
    public ResponseEntity<SellerProductDto> createEntity(@PathVariable String id, @RequestBody @Valid SellerProductForm priceForm, HttpServletRequest request){
        BaseProduct baseProduct = baseProductService.findEntityById(Long.valueOf(id));
        return ResponseEntity.status(201).body(sellerProductService.createEntity(baseProduct,priceForm, request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SellerProductDto> updateEntity(@PathVariable String id, @RequestBody SellerProductForm sellerProductForm, HttpServletRequest request){
        return ResponseEntity.ok(sellerProductService.updateEntity(Long.valueOf(id), sellerProductForm, request));
    }
    @GetMapping
    public ResponseEntity<List<SellerProductCompleteDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(sellerProductService.listDtoPagination(Integer.valueOf(page)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerProductCompleteDto> findById(@PathVariable String id){
        return ResponseEntity.status(201).body(sellerProductService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(sellerProductService.deleteById(Long.valueOf(id), request));
    }
}
