package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ItemService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;

@Tag(name = "Shopping cart")
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ItemService itemService;
    @Operation(summary = "find cart by id")
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartCompleteDto> findEntityById(@Parameter(description = "find cart by id", example = "1")@PathVariable String id){
        return ResponseEntity.ok(shoppingCartService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "delete cart by id")
    @ApiResponse(responseCode = "200", description = "Cart deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletedById(@Parameter(description = "delete cart find it by id", example = "1") @PathVariable String id, HttpServletResponse response){
        return ResponseEntity.ok(shoppingCartService.deleteById(Long.valueOf(id), response));
    }
    @Operation(summary = "get a cart list of ten by page")
    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getDtoListPagination(@Parameter(description = "get a list by page", example = "0") @RequestParam String page){
        return ResponseEntity.ok(shoppingCartService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "create cart")
    @PostMapping
    public ResponseEntity<ShoppingCartDto> createEntity(@RequestBody @Valid ShoppingCartForm shoppingCartDto, HttpServletResponse response) throws IOException {
        return ResponseEntity.status(201).body(shoppingCartService.createEntity(shoppingCartDto, response));
    }
    @Operation(summary = "cart update by id")
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> updateEntity(@Parameter(description = "find user by id", example = "1") @PathVariable String id, @RequestBody ShoppingCartForm shoppingCartDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(shoppingCartService.updateEntityById(Long.valueOf(id), shoppingCartDto, request, response));
    }
    @Operation(summary = "add a new product by id to shopping cart by id")
    @PostMapping("/{id}/product/{idProduct}")
    public ResponseEntity<ShoppingCartCompleteDto> addNewProductToCart(@Parameter(description = "find cart by id", example = "1") @PathVariable String id, @Parameter(description = "find product by id", example = "1")@PathVariable String idProduct, @Parameter(description = "put the amount of products that you want", example = "1") @RequestParam(required = false) String amount){
        return ResponseEntity.ok(shoppingCartService.addProductToCart(Long.valueOf(id), Long.valueOf(idProduct), Integer.valueOf(amount)));
    }
    @Operation(summary = "update a item by id to shopping cart by id")
    @PutMapping("/{id}/item/{idItem}")
    public ResponseEntity<ShoppingCartCompleteDto> updateOrderById(@Parameter(description = "find product by id", example = "1") @PathVariable String id, @Parameter(description = "find item by id", example = "1") @PathVariable String idItem, @Parameter(description = "find product by id", example = "1") @RequestParam(required = false) Long product, @Parameter(description = "put the amounts of products that you want", example = "1")@RequestParam(required = false) String amount){
        return ResponseEntity.ok(shoppingCartService.updateProductToCart(Long.valueOf(id), Long.valueOf(idItem), product, Integer.valueOf(amount)));
    }
    @Operation(summary = "remove a item by id to shopping cart by id")
    @DeleteMapping("/{id}/item/{idItem}")
    public ResponseEntity<ShoppingCartCompleteDto> deletedOrderById(@Parameter(description = "find product by id", example = "1") @PathVariable String id, @Parameter(description = "find item by id", example = "1") @PathVariable String idItem){
        itemService.deleteById(Long.valueOf(idItem));
        return ResponseEntity.ok(shoppingCartService.findById(Long.valueOf(id)));
    }
}
