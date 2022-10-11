package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.managerService.CustomizationAllowedService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;
@Tag(name = "Manager product - area")
@RestController
@RequestMapping("/manager/area")
public class AreaEnabledController {
    @Autowired
    private BaseProductService productService;
    @Autowired
    private EnabledAreaService enabledAreaService;
    @Autowired
    private CustomizationAllowedService customizationAllowedService;

    @Operation(summary = "find area by id")
    @GetMapping("/{id}")
    ResponseEntity<EnabledAreaCompleteDto> findEntityById(@Parameter(description = "insert area id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(enabledAreaService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "get 10 areas list by page")
    @GetMapping
    ResponseEntity<List<EnabledAreaCompleteDto>> findEntitiesListPage(@Parameter(description = "insert page number", example = "0")@RequestParam String page){
        return ResponseEntity.ok(enabledAreaService.findDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "create area")
    @PostMapping
    ResponseEntity<EnabledAreaDto> createEntity(@RequestBody @Valid EnabledAreaDto enabledAreaDto){
        return new ResponseEntity<>(enabledAreaService.createEntity(enabledAreaDto), HttpStatus.CREATED);
    }
    @Operation(summary = "update area by id")
    @PutMapping("/{id}")
    ResponseEntity<EnabledAreaDto> updateEntity(@Parameter(description = "insert area id", example = "1")@PathVariable String id, @RequestBody EnabledAreaDto enabledAreaDto){
        return ResponseEntity.ok(enabledAreaService.updateEntity(Long.valueOf(id), enabledAreaDto));
    }
    @Operation(summary = "delete area by id")
    @ApiResponse(responseCode = "200", description = "Area deleted",
            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MESSAGE_DELETE),}) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(enabledAreaService.deleteEntityById(Long.valueOf(id)));
    }
    @Operation(summary = "find area by id and add a new customization")
    @PostMapping("/{id}/customization")
    ResponseEntity<EnabledAreaCompleteDto> addAreaToProduct(@Parameter(description = "insert area id", example = "1")@PathVariable String id, @RequestBody @Valid CustomizationAllowedDto dto){
        CustomizationAllowed  customizationAllowed = customizationAllowedService.findByType(dto.getType());
        return ResponseEntity.ok(enabledAreaService.addCustomizationAllowedToEntity(Long.valueOf(id), customizationAllowed));
    }
    @Operation(summary = "find area by id and remove a customization")
    @DeleteMapping("/{id}/customization")
    ResponseEntity<EnabledAreaCompleteDto> removeAreaToProduct(@Parameter(description = "insert area id", example = "1")@PathVariable String id, @RequestBody @Valid CustomizationAllowedDto dto){
        CustomizationAllowed  customizationAllowed = customizationAllowedService.findByType(dto.getType());
        return ResponseEntity.ok(enabledAreaService.removeCustomizationAllowedToEntity(Long.valueOf(id), customizationAllowed));
    }
}
