package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.service.managerService.CustomizationAllowedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;
@CrossOrigin(origins = "https://sofftek-e-commerce.herokuapp.com/"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Manager product - customization")
@RestController
@RequestMapping("/manager/customization")
public class CustomizationAllowedController {
    @Autowired
    private CustomizationAllowedService customizationAllowedService;
    @Operation(summary = "find customization by id")
    @GetMapping("/{id}")
    ResponseEntity<CustomizationAllowedDto> getEntityById(@Parameter(description = "insert customization id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(customizationAllowedService.findDtoById(Long.valueOf(id)));
    }
    @Operation(summary = "get 10 customization list by page")
    @GetMapping
    ResponseEntity<List<CustomizationAllowedDto>> getListEntityPagination(@Parameter(description = "insert a page number", example = "0")@RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(customizationAllowedService.findDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "delete customization by id")
    @ApiResponse(responseCode = "200", description = "Customization deleted",
            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MESSAGE_DELETE),}) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(customizationAllowedService.deleteEntityById(Long.valueOf(id)));
    }
    @Operation(summary = "create a customization")
    @PostMapping
    ResponseEntity<CustomizationAllowedDto> createEntity(@RequestBody @Valid CustomizationAllowedDto customizationAllowedDto){
        return ResponseEntity.status(201).body(customizationAllowedService.createEntity(customizationAllowedDto));
    }
    @Operation(summary = "update customization by id")
    @PutMapping("/{id}")
    ResponseEntity<CustomizationAllowedDto> updateEntity(@Parameter(description = "insert customization id", example = "1")@PathVariable String id, @RequestBody CustomizationAllowedDto customizationAllowedDto){
        return ResponseEntity.ok(customizationAllowedService.updateEntity(Long.valueOf(id), customizationAllowedDto));
    }
}
