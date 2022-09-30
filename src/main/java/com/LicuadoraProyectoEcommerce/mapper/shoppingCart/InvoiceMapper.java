package com.LicuadoraProyectoEcommerce.mapper.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.InvoiceDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Invoice;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InvoiceMapper {
    public InvoiceDto getDtoFromEntity(Invoice invoice){
        return new InvoiceDto(invoice.getId(), invoice.getStatus(), invoice.getInvoiceNumber(), invoice.getPayment());
    }
    public Invoice updateFromForm(Invoice invoice, InvoiceForm invoiceForm){
        Stream.of(invoiceForm).forEach(f-> {
            if(f.getInvoiceNumber()!= null) invoice.setInvoiceNumber(f.getInvoiceNumber());
        });
        return invoice;
    }
    public Invoice createFromForm(Payment payment, InvoiceForm invoiceForm){
        return new Invoice(payment, invoiceForm.getInvoiceNumber());
    }
    public List<InvoiceDto> getListDtoFromListEntity(List<Invoice> list ){
        return list.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}
