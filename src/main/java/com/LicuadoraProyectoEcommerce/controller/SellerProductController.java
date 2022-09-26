package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductPriceForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.service.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sellerProduct")
public class SellerProductController {
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private BaseProductService baseProductService;
    @Autowired
    private UserAuthService userAuthService;
    @PostMapping("/{id}")
    public ResponseEntity<SellerProductDto> createEntity(@PathVariable String id, @RequestBody SellerProductPriceForm priceForm, HttpServletRequest request){
        BaseProduct baseProduct = baseProductService.findEntityById(Long.valueOf(id));
        return ResponseEntity.status(201).body(sellerProductService.createEntity(baseProduct,priceForm, request));
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
        //        userAuthService.isSellerProductSellerCreator(request, sellerProductService.findEntityById(Long.valueOf(id))); //TODO PRUEBA DE FILTRO POR USUARIO CREADOR DEL PRODUCTO
        return ResponseEntity.status(201).body(sellerProductService.deleteById(Long.valueOf(id)));
    }
}
