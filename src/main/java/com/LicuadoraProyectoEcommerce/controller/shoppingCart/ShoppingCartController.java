package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ItemService;
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
    @Autowired
    private ItemService itemService;
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartCompleteDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(shoppingCartService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletedById(@PathVariable String id){
        return ResponseEntity.ok(shoppingCartService.deleteById(Long.valueOf(id)));
    }
    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getDtoListPagination(@RequestParam String page){
        return ResponseEntity.ok(shoppingCartService.geDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping
    public ResponseEntity<ShoppingCartDto> createEntity(@RequestBody @Valid ShoppingCartForm shoppingCartDto){
        return ResponseEntity.status(201).body(shoppingCartService.createEntity(shoppingCartDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> updateEntity(@PathVariable String id, @RequestBody ShoppingCartForm shoppingCartDto){
        return ResponseEntity.ok(shoppingCartService.updateEntityById(Long.valueOf(id), shoppingCartDto));
    }
    @PostMapping("/{id}/product/{idProduct}")
    public ResponseEntity<ShoppingCartCompleteDto> addNewProductToCart(@PathVariable String id, @PathVariable String idProduct, @RequestParam(required = false) String amount){
        return ResponseEntity.ok(shoppingCartService.addProductToCart(Long.valueOf(id), Long.valueOf(idProduct), Integer.valueOf(amount)));
    }
    @PutMapping("/{id}/item/{idItem}")
    public ResponseEntity<ShoppingCartCompleteDto> updateOrderById(@PathVariable String id, @PathVariable String idItem, @RequestParam(required = false) Long product, @RequestParam(required = false) String amount){
        return ResponseEntity.ok(shoppingCartService.updateProductToCart(Long.valueOf(id), Long.valueOf(idItem), product, Integer.valueOf(amount)));
    }
    @DeleteMapping("/{id}/item/{idItem}")
    public ResponseEntity<ShoppingCartCompleteDto> deletedOrderById(@PathVariable String id, @PathVariable String idItem){
        itemService.deleteById(Long.valueOf(idItem));
        return ResponseEntity.ok(shoppingCartService.findById(Long.valueOf(id)));
    }

}
