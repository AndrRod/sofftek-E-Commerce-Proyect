package com.LicuadoraProyectoEcommerce.controller.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.form.InvoicePdfPrintForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.service.sellerService.InvoiceService;
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
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.MESSAGE_DELETE;

@Tag(name = "Seller invoice")
@RestController
@RequestMapping("/seller/invoice")
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
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable String id, HttpServletRequest request){
        return ResponseEntity.ok(invoiceService.deleteById(Long.valueOf(id), request));
    }
    @Operation(summary = "get a invoice list of ten entities")
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getDtoListPagination(@Parameter(description = "insert a number page", example = "0") @RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(invoiceService.geDtoListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "create a invoice for a purchase that find by id")
    @PostMapping("/purchase/{idPurchase}")
    public ResponseEntity<InvoiceDto> createEntity(@Parameter(description = "find purchase by id to add to the invoice", example = "1") @PathVariable String idPurchase, @RequestBody @Valid InvoiceForm invoiceForm, HttpServletRequest request) throws IOException {
        return ResponseEntity.status(201).body(invoiceService.createEntity(Long.valueOf(idPurchase), invoiceForm, request));
    }
    @Operation(summary = "update a invoice by id, and the possibility tu change the purchase by id")
    @PutMapping("/{id}/purchase/{idPurchase}")
    public ResponseEntity<InvoiceDto> updateEntity(@Parameter(description = "insert invoice id", example = "1")@PathVariable String id, @Parameter(description = "find purchase by id to update the invoice",example = "1")@PathVariable(required = false) Long idPurchase, @RequestBody @Valid InvoiceForm invoiceForm, HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(invoiceService.updateEntity(Long.valueOf(id), idPurchase, invoiceForm, request));
    }
    @Operation(summary = "print invoice by id")
    @PostMapping("/{id}/print")
    public ResponseEntity<MessageInfo> createPrintInvoiceById(@Parameter(description = "insert invoice id", example = "1") @PathVariable String id, @RequestBody @Valid InvoicePdfPrintForm invoiceForm, HttpServletRequest request) throws IOException {
        invoiceService.printInvoiceById(Long.valueOf(id), invoiceForm, request);
        return ResponseEntity.ok(new MessageInfo("the invoice pdf was create successfully", 201, request.getRequestURI()));
    }
}
