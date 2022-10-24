package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;
import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.PURCHASE_MESSAGE_UPDATE_STATE;
@CrossOrigin(origins = "https://sofftek-e-commerce.herokuapp.com/"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Shopping Cart Purchase")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Operation(summary = "find purchase by id")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findEntityById(@Parameter(description = "insert purchase id", example = "1")@PathVariable String id){
        return ResponseEntity.ok(purchaseService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "delete purchase by id")
    @ApiResponse(responseCode = "200", description = "purchase deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@Parameter(description = "insert purchase id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(purchaseService.deleteById(Long.valueOf(id)));
    }
    @Operation(summary = "get a purchase list of ten by page")
    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getDtoListPagination(@Parameter(description = "get a list by page", example = "0") @RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(purchaseService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "create purchase for a shopping cart")
    @PostMapping("/cart/{idShoppingCart}")
    public ResponseEntity<PurchaseDto> createEntity(@Parameter(description = "insert cart id", example = "1") @PathVariable String idShoppingCart, @RequestBody @Valid PaymentForm paymentForm){
        return new ResponseEntity<>(purchaseService.createEntity(Long.valueOf(idShoppingCart), paymentForm), HttpStatus.CREATED);
    }
    @Operation(summary = "update purchase by id, and possibility to change with another shopping cart")
    @PutMapping("/{id}/cart/{idShoppingCart}")
    public ResponseEntity<PurchaseDto> updateEntity(@Parameter(description = "insert purchase id", example = "1") @PathVariable String id, @Parameter(description = "find a shopping cart by id", example = "1") @PathVariable(required = false) Long idShoppingCart, @RequestBody PaymentForm paymentForm){
        return ResponseEntity.ok(purchaseService.updateEntity(Long.valueOf(id), idShoppingCart, paymentForm));
    }
    @Operation(summary = "update the purchase state by id")
    @ApiResponse(responseCode = "200", description = "purchase updated",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = PURCHASE_MESSAGE_UPDATE_STATE)}) })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePurchaseStatus(@Parameter(description = "insert purchase id", example = "1") @PathVariable String id, @Parameter(description = "insert a payment method", example = "ACCEPTED") @RequestParam String payment, HttpServletRequest request){
        return ResponseEntity.ok(purchaseService.updateStatePurchase(Long.valueOf(id), payment, request));
    }
}
