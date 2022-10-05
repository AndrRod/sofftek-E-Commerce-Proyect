package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ProductDto;
import com.LicuadoraProyectoEcommerce.form.PublicationForm;
import com.LicuadoraProyectoEcommerce.model.seller.Publication;
import com.LicuadoraProyectoEcommerce.service.sellerService.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("seller/publication")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> findEntityById(@PathVariable String id){
        return ResponseEntity.ok(publicationService.findById(Long.valueOf(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(publicationService.deleteById(Long.valueOf(id), request));
    }
    @GetMapping
    public ResponseEntity<List<PublicationDto>> getDtoListPagination(@RequestParam String page, @RequestParam(required = false) String state){
        return ResponseEntity.ok(publicationService.getListEntityPage(Integer.valueOf(page), state));
    }
    @PostMapping("/product/{idProduct}")
    public ResponseEntity<PublicationDto> createEntity(@PathVariable String idProduct, HttpServletRequest request, @RequestBody @Valid PublicationForm publicationForm){
        return ResponseEntity.status(201).body(publicationService.createEntity(Long.valueOf(idProduct), publicationForm, request));
    }
    @PutMapping("/{id}/product/{idProduct}")
    public ResponseEntity<PublicationDto> updateEntity(@PathVariable String id, @PathVariable(required = false) Long idProduct, @RequestBody PublicationForm publicationForm, HttpServletRequest request){
        return ResponseEntity.ok(publicationService.updateEntity(Long.valueOf(id), idProduct, publicationForm, request));
    }

}
