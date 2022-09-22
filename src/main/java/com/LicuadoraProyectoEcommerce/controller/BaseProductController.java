package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.managerService.EnabledAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private EnabledAreaService enabledAreaService;
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
    @PostMapping("/{id}/addArea/{idArea}")
    ResponseEntity<BaseProductDtoComplete> addAreaToProduct(@PathVariable String id, @PathVariable String idArea){
        EnabledArea enabledArea = enabledAreaService.findEntityById(Long.valueOf(idArea));
        return ResponseEntity.ok(baseProductService.addEnabledAreaToEntity(Long.valueOf(id), enabledArea));
    }
    @PostMapping("/{id}/removeArea/{idArea}")
    ResponseEntity<BaseProductDtoComplete> removeAreaToProduct(@PathVariable String id, @PathVariable String idArea){
        EnabledArea enabledArea = enabledAreaService.findEntityById(Long.valueOf(idArea));
        return new ResponseEntity<>(baseProductService.removeEnabledAreaToEntity(Long.valueOf(id), enabledArea), HttpStatus.OK);
    }
}
