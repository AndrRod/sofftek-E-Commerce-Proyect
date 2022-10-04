package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.InvoiceDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.InvoiceMapper;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Invoice;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.InvoiceRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.InvoiceService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private PurchaseService purchaseService;
    @Override
    public InvoiceDto createEntity(Long idPayment, InvoiceForm invoiceForm) {
        Purchase purchase = purchaseService.findEntityById(idPayment);
        Try.of(()->StatusPayment.valueOf(invoiceForm.getStatus())).getOrElseThrow(()-> new NotFoundException("the "+ invoiceForm.getStatus() + " status doesn't exists"));
        Invoice invoice = invoiceMapper.createFromForm(purchase, invoiceForm);
        return invoiceMapper.getDtoFromEntity(invoiceRepository.save(invoice));
    }
    @Override
    public InvoiceDto updateEntity(Long id, Long idPayment, InvoiceForm invoiceForm) {
        Invoice invoice = findEntityById(id);
        if(idPayment!=null && idPayment != invoice.getPurchase().getId()){
            Purchase purchase = purchaseService.findEntityById(idPayment);
            if(invoiceRepository.existsByPurchase(purchase)) throw new BadRequestException("the payment already have invoice");
            invoice.setPurchase(purchase);
        }
        Try.of(()->StatusPayment.valueOf(invoiceForm.getStatus())).getOrElseThrow(()-> new NotFoundException("the "+ invoiceForm.getStatus() + " status doesn't exists"));
        Invoice invoiceUpdate = invoiceMapper.updateFromForm(invoice, invoiceForm);
        return invoiceMapper.getDtoFromEntity(invoiceUpdate);
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        invoiceRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public InvoiceDto findById(Long id) {
        return invoiceMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Invoice findEntityById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<InvoiceDto> geDtoListPagination(Integer page) {
        List<Invoice> listEntities = invoiceRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return invoiceMapper.getListDtoFromListEntity(listEntities);
    }
}
