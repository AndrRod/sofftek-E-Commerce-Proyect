package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.form.PublicationForm;
import com.LicuadoraProyectoEcommerce.service.sellerService.PublicationService;
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
@Tag(name = "Seller product - Publication")
@RestController
@RequestMapping("seller/publication")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Operation(summary = "find publication by id")
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> findEntityById(@Parameter(description = "insert a publication id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(publicationService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "delete publication by id")
    @ApiResponse(responseCode = "200", description = "publication deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(publicationService.deleteById(Long.valueOf(id), request));
    }
    @Operation(summary = "get 10 publication list by page and optionally by state")
    @GetMapping
    public ResponseEntity<List<PublicationDto>> getDtoListPagination(@Parameter(description = "insert a page number", example = "1")@RequestParam(defaultValue = "0", required = false) String page, @Parameter(description = "insert a state", example = "PUBLISHED") @RequestParam(defaultValue = "0", required = false) String state){
        return ResponseEntity.ok(publicationService.getListEntityPage(Integer.valueOf(page), state));
    }
    @Operation(summary = "create a publication by product id")
    @PostMapping("/product/{idProduct}")
    public ResponseEntity<PublicationDto> createEntity(@Parameter(description = "insert product id", example = "1") @PathVariable String idProduct, HttpServletRequest request, @RequestBody @Valid PublicationForm publicationForm){
        return ResponseEntity.status(201).body(publicationService.createEntity(Long.valueOf(idProduct), publicationForm, request));
    }
    @Operation(summary = "update a publication by id")
    @PutMapping("/{id}/product/{idProduct}")
    public ResponseEntity<PublicationDto> updateEntity(@Parameter(description = "insert publication id", example = "1")@PathVariable String id,@Parameter(description = "optionally insert product id", example = "1") @PathVariable(required = false) Long idProduct, @RequestBody PublicationForm publicationForm, HttpServletRequest request){
        return ResponseEntity.ok(publicationService.updateEntity(Long.valueOf(id), idProduct, publicationForm, request));
    }

}
