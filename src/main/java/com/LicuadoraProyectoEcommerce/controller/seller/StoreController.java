package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.StoreService;
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
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;
@CrossOrigin(origins = "*"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Seller Store")
@RestController
@RequestMapping("/seller/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Operation(summary = "find by id and by list product list by page and the possibility to find by state (PUBLISHED, PAUSED, CANCELLED, FINISHED)")
    @GetMapping("/{id}/publications")
    public ResponseEntity<SellerStoreCompleteDto> findEntityById(@Parameter(description = "insert store id", example = "1") @PathVariable String id,@Parameter(description = "insert page", example = "0") @RequestParam(required = false, defaultValue = "0") Integer page,@Parameter(description = "insert state", example = "PUBLISHED") @RequestParam(required = false) String state){
        return ResponseEntity.ok(storeService.findById(Long.valueOf(id), page, state));
    }
    @Operation(summary = "get a store list of ten by page")
    @GetMapping
    public ResponseEntity<List<SellerStoreDto>> getListStorePagination(@Parameter(description = "insert the number page", example = "0")@RequestParam(defaultValue = "0", required = false) String page){
        return  ResponseEntity.ok(storeService.listDtoPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "delete store by id")
    @ApiResponse(responseCode = "200", description = "store deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(storeService.deleteById(Long.valueOf(id), request));
    }
    @Operation(summary = "create store")
    @PostMapping
    public ResponseEntity<SellerStoreCompleteDto> createEntity(@RequestBody @Valid SellerStoreDto sellerStoreDto, HttpServletRequest request){
        return ResponseEntity.status(201).body(storeService.createEntity(sellerStoreDto, request));
    }
    @Operation(summary = "update store by id")
    @PutMapping("/{id}")
    public ResponseEntity<SellerStoreCompleteDto> updateEntity(@Parameter(description = "insert store id", example = "1")@PathVariable String id, @RequestBody SellerStoreDto sellerStoreDto, HttpServletRequest request){
        return ResponseEntity.ok(storeService.updateEntity(Long.valueOf(id),sellerStoreDto, request));
    }
    @Operation(summary = "add a payment method to the store found by id")
    @PostMapping("/{id}/payment")
    public ResponseEntity<SellerStoreCompleteDto> addNewPaymentMethod(@Parameter(description = "insert store id id", example = "1")@PathVariable String id, @Parameter(description = "inserted payment method name")@RequestParam String method, HttpServletRequest request){
        return ResponseEntity.status(201).body(storeService.addNewPaymentMethod(Long.valueOf(id), method,request));
    }
    @Operation(summary = "remove a payment method to the store found by id")
    @DeleteMapping("/{id}/payment")
    public ResponseEntity<SellerStoreCompleteDto> removePaymentMethod(@Parameter(description = "insert sotre id", example = "1")@PathVariable String id, @Parameter(description = "inserted payment method name")@RequestParam String method, HttpServletRequest request){
        return ResponseEntity.status(200).body(storeService.removePaymentMethod(Long.valueOf(id), method, request));
    }

}
