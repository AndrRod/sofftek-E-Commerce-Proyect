package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Seller product - Area")
@RestController
@RequestMapping("/seller/area")
public class SellerAreaController {
    @Autowired
    private SellerAreaService sellerAreaService;
    @Operation(summary = "get 10 areas list by page")
    @GetMapping
    public ResponseEntity<List<SellerAreaCompleteDto>> getListDtoPagination(@Parameter(description = "insert the page number", example = "0") @RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(sellerAreaService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "find area by id")
    @GetMapping("/{id}")
    public ResponseEntity<SellerAreaDto> findById(@Parameter(description = "insert an area id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(sellerAreaService.findById(Long.valueOf(id)));
    }
}