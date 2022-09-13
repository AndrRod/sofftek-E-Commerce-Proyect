package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.service.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baseproduct")
public class BaseProductController {
    @Autowired
    private BaseProductService baseProductService;
    @PostMapping
    ResponseEntity<BaseProductDtoComplete> createProduct(@RequestBody @Valid BaseProductDto productDto){
        return ResponseEntity.status(201).body(baseProductService.createBaseProduct(productDto));
    }
    @GetMapping("/{id}")
    ResponseEntity<BaseProductDtoComplete> getProductById(@PathVariable String id){
        return ResponseEntity.ok(baseProductService.getBaseProductById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<BaseProductDtoComplete>> getProductPage(@RequestParam String page){
        return ResponseEntity.ok(baseProductService.getBaseProductListPage(Integer.valueOf(page)));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteProductById(@PathVariable String id){
        return ResponseEntity.ok(baseProductService.deleteBaseProductById(Long.valueOf(id)));
    }
    @PutMapping("/{id}")
    ResponseEntity<BaseProductDtoComplete> updateProduct(@PathVariable String id, @RequestBody BaseProductDto baseProductDto){
        return ResponseEntity.ok(baseProductService.updateBaseProduct(Long.valueOf(id), baseProductDto));
    }
}