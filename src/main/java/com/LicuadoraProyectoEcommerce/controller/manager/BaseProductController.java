package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.managerService.EnabledAreaService;
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

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.*;

@Tag(name = "Manager - Product")
@RestController
@RequestMapping("/manager/product")
public class BaseProductController {

    @Autowired
    private BaseProductService baseProductService;
    @Autowired
    private EnabledAreaService enabledAreaService;
    @Autowired
    private UserAuthService userAuthService;

    @Operation(summary = "create a new product")
//    @ApiResponse(responseCode = "201", description = "product created",
//            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MANAGER_MESSAGE_PRODUCT)}) })
    @PostMapping
    ResponseEntity<BaseProductDtoComplete> createProduct(@RequestBody @Valid BaseProductDto productDto, HttpServletRequest request){
        return ResponseEntity.status(201).body(baseProductService.createBaseProduct(productDto, request));
    }
    @Operation(summary = "find product by id")
//    @ApiResponse(responseCode = "200", description = "product found",
//            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MANAGER_MESSAGE_PRODUCT)}) })
    @GetMapping("/{id}")
    ResponseEntity<BaseProductDtoComplete> getProductById(@Parameter(example = "1", description = "find by id") @PathVariable String id){
        return ResponseEntity.ok(baseProductService.getBaseProductById(Long.valueOf(id)));
    }
    @Operation(summary = "get a list of product paginated")
//    @ApiResponse(responseCode = "200", description = "get product list",
//            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MANAGER_MESSAGE_PRODUCT_PAGE)}) })
    @GetMapping
    ResponseEntity<List<BaseProductDtoComplete>> getProductPage(@RequestParam String page){
        return ResponseEntity.ok(baseProductService.getBaseProductListPage(Integer.valueOf(page)));
    }
    @Operation(summary = "delete by id")
//    @ApiResponse(responseCode = "200", description = "deleted entity by id",
//            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteProductById(@Parameter(example = "1", description = "insert a id")@PathVariable String id){
        return ResponseEntity.ok(baseProductService.deleteBaseProductById(Long.valueOf(id)));
    }
    @Operation(summary = "update by id")
//    @ApiResponse(responseCode = "200", description = "update entity by id",
//            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MANAGER_MESSAGE_PRODUCT)}) })
    @PutMapping("/{id}")
    ResponseEntity<BaseProductDtoComplete> updateProduct(@Parameter(example = "1", description = "insert a id") @PathVariable String id, @RequestBody BaseProductDto baseProductDto, HttpServletRequest request){
        return ResponseEntity.ok(baseProductService.updateBaseProduct(Long.valueOf(id), baseProductDto, request));
    }
    @Operation(summary = "find a product by id and add a new area by id")
    @PostMapping("/{id}/area/{idArea}")
    ResponseEntity<BaseProductDtoComplete> addAreaToProduct(@PathVariable String id, @PathVariable String idArea, HttpServletRequest request){
        EnabledArea enabledArea = enabledAreaService.findEntityById(Long.valueOf(idArea));
        return ResponseEntity.ok(baseProductService.addEnabledAreaToEntity(Long.valueOf(id), enabledArea, request));
    }
    @Operation(summary = "find a product by id and remove a area by id")
    @DeleteMapping("/{id}/area/{idArea}")
    ResponseEntity<BaseProductDtoComplete> removeAreaToProduct(@PathVariable String id, @PathVariable String idArea, HttpServletRequest request){
        EnabledArea enabledArea = enabledAreaService.findEntityById(Long.valueOf(idArea));
        return new ResponseEntity<>(baseProductService.removeEnabledAreaToEntity(Long.valueOf(id), enabledArea, request), HttpStatus.OK);
    }
}
