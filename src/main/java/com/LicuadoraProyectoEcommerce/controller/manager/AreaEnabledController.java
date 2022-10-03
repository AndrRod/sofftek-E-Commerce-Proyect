package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import com.LicuadoraProyectoEcommerce.service.managerService.CustomizationAllowedService;
import com.LicuadoraProyectoEcommerce.service.managerService.EnabledAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager/area")
public class AreaEnabledController {
    @Autowired
    private BaseProductService productService;
    @Autowired
    private EnabledAreaService enabledAreaService;
    @Autowired
    private CustomizationAllowedService customizationAllowedService;

    @GetMapping("/{id}")
    ResponseEntity<EnabledAreaDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(enabledAreaService.findById(Long.valueOf(id)));
    }
    @GetMapping
    ResponseEntity<List<EnabledAreaCompleteDto>> findEntitiesListPage(@RequestParam String page){
        return ResponseEntity.ok(enabledAreaService.findDtoListPagination(Integer.valueOf(page)));
    }
    @PostMapping
    ResponseEntity<EnabledAreaDto> createEntity(@RequestBody @Valid EnabledAreaDto enabledAreaDto){
        return new ResponseEntity<>(enabledAreaService.createEntity(enabledAreaDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    ResponseEntity<EnabledAreaDto> updateEntity(@PathVariable String id, @RequestBody EnabledAreaDto enabledAreaDto){
        return ResponseEntity.ok(enabledAreaService.updateEntity(Long.valueOf(id), enabledAreaDto));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(enabledAreaService.deleteEntityById(Long.valueOf(id)));
    }
    @PostMapping("/{id}/customization")
    ResponseEntity<EnabledAreaCompleteDto> addAreaToProduct(@PathVariable String id, @RequestBody @Valid CustomizationAllowedDto dto){
        CustomizationAllowed  customizationAllowed = customizationAllowedService.findByTypeAndName(dto.getType());
        return ResponseEntity.ok(enabledAreaService.addCustomizationAllowedToEntity(Long.valueOf(id), customizationAllowed));
    }
    @DeleteMapping("/{id}/customization")
    ResponseEntity<EnabledAreaCompleteDto> removeAreaToProduct(@PathVariable String id, @RequestBody @Valid CustomizationAllowedDto dto){
        CustomizationAllowed  customizationAllowed = customizationAllowedService.findByTypeAndName(dto.getType());
        return ResponseEntity.ok(enabledAreaService.removeCustomizationAllowedToEntity(Long.valueOf(id), customizationAllowed));
    }
}
