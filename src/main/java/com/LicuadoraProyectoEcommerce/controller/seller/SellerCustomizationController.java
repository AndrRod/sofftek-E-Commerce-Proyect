package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/customization")
public class SellerCustomizationController {
    @Autowired
    private SellerCustomizationService sellerCustomizationService;
    @GetMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> findById(@PathVariable String id){
        return ResponseEntity.ok(sellerCustomizationService.findById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<SellerCustomizationCompleteDto>> getListPagination(@RequestParam String page){
        return ResponseEntity.ok(sellerCustomizationService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> updateEntity(@PathVariable String id, @RequestBody SellerCustomizationDto customizationDto, HttpServletRequest request){
        return ResponseEntity.ok(sellerCustomizationService.updateEntity(Long.valueOf(id), customizationDto, request));
    }
    @DeleteMapping("/{id}/reset")
    ResponseEntity<Map<String, String>> deleteEntityParams(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(sellerCustomizationService.deleteEntityParams(Long.valueOf(id), request));
    }
}
