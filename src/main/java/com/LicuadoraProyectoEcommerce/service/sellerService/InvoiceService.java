package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.model.seller.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceService {
    InvoiceDto createEntity(Long idPayment, InvoiceForm invoiceForm);
    InvoiceDto updateEntity(Long id, Long idPayment, InvoiceForm invoiceForm);
    Map<String, String> deleteById(Long id);
    InvoiceDto findById(Long id);
    Invoice findEntityById(Long id);
    List<InvoiceDto> geDtoListPagination(Integer page);
}