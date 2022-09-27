package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartCompleteDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(shoppingCartService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(shoppingCartService.deleteById(Long.valueOf(id)));
    }
    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(shoppingCartService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping
    public ResponseEntity<ShoppingCartDto> createEntity(@RequestBody @Valid ShoppingCartDto shoppingCartDto){
        return ResponseEntity.status(201).body(shoppingCartService.createEntity(shoppingCartDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> updateEntity(@PathVariable String id, @RequestBody ShoppingCartDto shoppingCartDto){
        return ResponseEntity.ok(shoppingCartService.updateEntityById(Long.valueOf(id), shoppingCartDto));
    }
}
