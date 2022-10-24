package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
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
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.CUSTOMIZATION_MESSAGE_RESET_PARAMTERS;

@CrossOrigin(origins = "https://sofftek-e-commerce.herokuapp.com/"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Seller product - Customization")
@RestController
@RequestMapping("/seller/customization")
public class SellerCustomizationController {

    @Autowired
    private SellerCustomizationService sellerCustomizationService;
    @Operation(summary = "find customization by id")
    @GetMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> findById(@Parameter(description = "insert customization  id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(sellerCustomizationService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "get 10 customization list by page")
    @GetMapping
    ResponseEntity<List<SellerCustomizationCompleteDto>> getListPagination(@Parameter(description = "insert page number", example = "0")@RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(sellerCustomizationService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "update customization by id")
    @PostMapping("/{id}")
    ResponseEntity<SellerCustomizationCompleteDto> updateEntity(@Parameter(description = "insert customization id", example = "1")@PathVariable String id, @RequestBody SellerCustomizationDto customizationDto, HttpServletRequest request){
        return ResponseEntity.ok(sellerCustomizationService.updateEntity(Long.valueOf(id), customizationDto, request));
    }
    @Operation(summary = "reset the customization parameters by id")
    @ApiResponse(responseCode = "200", description = "parameters reset",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = CUSTOMIZATION_MESSAGE_RESET_PARAMTERS)}) })
    @DeleteMapping("/{id}/reset")
    ResponseEntity<Map<String, String>> deleteEntityParams(@Parameter(description = "insert customization id", example = "1") @PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(sellerCustomizationService.deleteEntityParams(Long.valueOf(id), request));
    }
}
