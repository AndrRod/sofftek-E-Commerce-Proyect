package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.form.SellerProductForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
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
@CrossOrigin(origins = "https://sofftek-e-commerce.herokuapp.com/"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Seller product")
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private BaseProductService baseProductService;
    @Autowired
    private UserAuthService userAuthService;
    @Operation(summary = "create seller product by finding base product id")
    @PostMapping("/{id}")
    public ResponseEntity<SellerProductDto> createEntity(@Parameter(description = "insert base product id", example = "1") @PathVariable String id, @RequestBody @Valid SellerProductForm priceForm, HttpServletRequest request){
        BaseProduct baseProduct = baseProductService.findEntityById(Long.valueOf(id));
        return ResponseEntity.status(201).body(sellerProductService.createEntity(baseProduct,priceForm, request));
    }
    @Operation(summary = "update seller product by id")
    @PutMapping("/{id}")
    public ResponseEntity<SellerProductDto> updateEntity(@Parameter(description = "insert product id", example = "1")@PathVariable String id, @RequestBody SellerProductForm sellerProductForm, HttpServletRequest request){
        return ResponseEntity.ok(sellerProductService.updateEntity(Long.valueOf(id), sellerProductForm, request));
    }
    @Operation(summary = "get 10 seller products list by page and optionally by state (PUBLISHED, PAUSED, CANCELLED, FINISHED)")
    @GetMapping
    public ResponseEntity<List<SellerProductDto>> getDtoListPagination(@Parameter(description = "insert page number", example = "1")@RequestParam(defaultValue = "0", required = false) String page, @Parameter(description = "find by state", example = "PUBLISHED")@RequestParam(required = false) String state){
        return ResponseEntity.ok(sellerProductService.listDtoPagination(Integer.valueOf(page), state));
    }
    @Operation(summary = "find seller product by id")
    @GetMapping("/{id}")
    public ResponseEntity<SellerProductCompleteDto> findById(@Parameter(description = "insert product  id", example = "1")@PathVariable String id){
        return ResponseEntity.ok(sellerProductService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "delete seller product by id")
    @ApiResponse(responseCode = "200", description = "store deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@Parameter(description = "insert product id", example = "1")@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(sellerProductService.deleteById(Long.valueOf(id), request));
    }
}
