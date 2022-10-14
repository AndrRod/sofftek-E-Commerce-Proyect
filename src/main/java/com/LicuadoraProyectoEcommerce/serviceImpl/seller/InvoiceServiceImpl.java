package com.LicuadoraProyectoEcommerce.serviceImpl.seller;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.apache.logging.log4j.Logger;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLD;
import static com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN;


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
    public InvoiceDto createEntity(Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request) throws IOException {
        Purchase purchase = purchaseService.findEntityById(idPayment);
        validPurchaseConditions(purchase);
        userAuthService.isSellerProductSellerCreator(request, purchase.getShoppingCart().getItems().get(0).getSellerProduct());
        Invoice invoice = invoiceMapper.createFromForm(purchase, invoiceForm);
        InvoiceDto invoiceDto = invoiceMapper.getDtoFromEntity(invoiceRepository.save(invoice));
        createInvoicePdf(invoiceDto);
        return invoiceDto;
    }
    @Override
    public InvoiceDto updateEntity(Long id, Long idPayment, InvoiceForm invoiceForm, HttpServletRequest request) throws IOException {
        Invoice invoice = findEntityById(id);
        if(idPayment!=null && idPayment != invoice.getPurchase().getId()){
            Purchase purchase = purchaseService.findEntityById(idPayment);
            validPurchaseConditions(purchase);
            invoice.setPurchase(purchase);
        }
        userAuthService.isSellerProductSellerCreator(request, invoice.getPurchase().getShoppingCart().getItems().get(0).getSellerProduct());
        Invoice invoiceUpdate = invoiceMapper.updateFromForm(invoice, invoiceForm);
        InvoiceDto invoiceDto = invoiceMapper.getDtoFromEntity(invoiceUpdate);
        createInvoicePdf(invoiceDto);
        return invoiceDto;
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

    void createInvoicePdf(InvoiceDto invoiceDto) throws IOException {
        String path = "invoice"+ invoiceDto.getInvoiceNumber() +".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(TIMES_BOLD);
        SolidLine line = new SolidLine(1f);
        Paragraph storeName = new Paragraph().add(invoiceDto.getSellerStoreName()).setFont(bold);
        Table table = new Table(new float[]{850});
        invoiceDto.getPurchaseList().forEach(p->{
           table.addCell(p).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED);
        });
        table.addCell(new Paragraph("Total Price: " + invoiceDto.getTotalPurchase()).setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph().add(storeName).add(" -  INVOICE NUMBER: ").add(invoiceDto.getInvoiceNumber()).setTextAlignment(TextAlignment.CENTER));
        document.add(new LineSeparator(line));
        document.add(new Paragraph().add("BUYER INFORMATION: ").setFont(bold));
        document.add(new Paragraph().add("Name: ").add(invoiceDto.getBuyerName()).add(", DNI: ").add(invoiceDto.getBuyerDni()).add(", Email: ").add(invoiceDto.getBuyerEmail()).setFont(font));
        document.add(new Paragraph().add("PURCHASE NUMBER: ").add(invoiceDto.getNumberOfTransaction()).setFont(bold));
        document.add(new Paragraph().add("PAYMENT METHOD: ").add(invoiceDto.getPaymentMethod()).add(" | PAYMENT STATUS: ").add(invoiceDto.getStatus()).setFont(bold));
        document.add(new LineSeparator(line));
        document.add(new Paragraph("Purchase Description: "));
        document.add(table);
        document.close();
    }

}
