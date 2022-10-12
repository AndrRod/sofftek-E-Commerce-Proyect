package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.InvoiceDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.mapper.seller.InvoiceMapper;
import com.LicuadoraProyectoEcommerce.model.seller.Invoice;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.LicuadoraProyectoEcommerce.repository.seller.InvoiceRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.sellerService.InvoiceService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UserAuthService userAuthService;
    @Override
    public InvoiceDto createEntity(Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request) {
        Purchase purchase = purchaseService.findEntityById(idPayment);
        validPurchaseConditions(purchase);
        userAuthService.isSellerProductSellerCreator(request, purchase.getShoppingCart().getItems().get(0).getSellerProduct());
        Invoice invoice = invoiceMapper.createFromForm(purchase, invoiceForm);
        return invoiceMapper.getDtoFromEntity(invoiceRepository.save(invoice));
    }
    @Override
    public InvoiceDto updateEntity(Long id, Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request) {
        Invoice invoice = findEntityById(id);
        if(idPayment!=null && idPayment != invoice.getPurchase().getId()){
            Purchase purchase = purchaseService.findEntityById(idPayment);
            validPurchaseConditions(purchase);
            invoice.setPurchase(purchase);
        }
        userAuthService.isSellerProductSellerCreator(request, invoice.getPurchase().getShoppingCart().getItems().get(0).getSellerProduct());
        Invoice invoiceUpdate = invoiceMapper.updateFromForm(invoice, invoiceForm);
        return invoiceMapper.getDtoFromEntity(invoiceUpdate);
    }
    void validPurchaseConditions(Purchase purchase){
        if(invoiceRepository.existsByPurchase(purchase)) throw new BadRequestException("the payment already have invoice");
        if(purchase.getStatus()!= StatusPayment.ACCEPTED) throw new BadRequestException("the payment its no already ACCEPTED, turn the purchase state to trigger the invoice");
    }

    @Override
    public Map<String, String> deleteById(Long id, HttpServletRequest request) {
        Invoice invoice = findEntityById(id);
        userAuthService.isSellerProductSellerCreator(request, invoice.getPurchase().getShoppingCart().getItems().get(0).getSellerProduct());
        invoiceRepository.delete(invoice);
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
