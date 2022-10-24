package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name="Seller")
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @Operation(summary = "find seller by id")
    @GetMapping("/{id}")
    ResponseEntity<SellerDto> getEntityById(@Parameter(description = "insert seller id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(sellerService.getById(Long.valueOf(id)));
    }
    @Operation(summary = "get 10 seller list by page")
    @GetMapping
    ResponseEntity<List<SellerDto>> getEntityListPage(@Parameter(description = "insert the page number", example = "0") @RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(sellerService.getListEntityPage(Integer.valueOf(page)));
    }

}
