package com.LicuadoraProyectoEcommerce.controller.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.InvoiceService;
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

@Tag(name = "Invoice")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Operation(summary = "find invoice by id")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> findEntityById(@Parameter(description = "insert invoice id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(invoiceService.findById(Long.valueOf(id)));
    }
    @Operation(summary = "delete invoice by id")
    @ApiResponse(responseCode = "200", description = "invoice deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(invoiceService.deleteById(Long.valueOf(id)));
    }
    @Operation(summary = "get a invoice list of ten entities")
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getDtoListPagination(@Parameter(description = "insert a number page", example = "0") @RequestParam String page){
        return ResponseEntity.ok(invoiceService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "create a invoice for a purchase that find by id")
    @PostMapping("/purchase/{idPurchase}")
    public ResponseEntity<InvoiceDto> createEntity(@Parameter(description = "find purchase by id to add to the invoice", example = "1") @PathVariable String idPurchase, @RequestBody @Valid InvoiceForm invoiceForm){
        return ResponseEntity.status(201).body(invoiceService.createEntity(Long.valueOf(idPurchase), invoiceForm));
    }
    @Operation(summary = "update a invoice by id, and the possibility tu change the purchase by id")
    @PutMapping("/{id}/purchase/{idPurchase}")
    public ResponseEntity<InvoiceDto> updateEntity(@Parameter(description = "insert invoice id", example = "1")@PathVariable String id, @Parameter(description = "find purchase by id to update the invoice",example = "1")@PathVariable(required = false) Long idPurchase, @RequestBody @Valid InvoiceForm invoiceForm){
        return ResponseEntity.ok(invoiceService.updateEntity(Long.valueOf(id), idPurchase, invoiceForm));
    }
}
