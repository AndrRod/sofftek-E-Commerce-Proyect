package com.LicuadoraProyectoEcommerce.service.sellerService;

import com.LicuadoraProyectoEcommerce.dto.seller.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.model.seller.Invoice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface InvoiceService {
    InvoiceDto createEntity(Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request);
    InvoiceDto updateEntity(Long id, Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request);
    Map<String, String> deleteById(Long id, HttpServletRequest requests);
    InvoiceDto findById(Long id);
    Invoice findEntityById(Long id);
    List<InvoiceDto> geDtoListPagination(Integer page);
}
