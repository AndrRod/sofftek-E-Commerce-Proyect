package com.LicuadoraProyectoEcommerce.model.listener;

import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.repository.seller.InvoiceRepository;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class SellerProductListener {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @PreUpdate
    @PreRemove
    //TODO CONTROLAR FUCIONAMIENTO deberia borrar producto solo si no hay compra pendiente de aprobarcion y factura
    public void preUpdateOrDeletePausedOrDeletedThePublication(SellerProduct sellerProduct){
        sellerProduct.getPublication().setPublicationSate(PublicationSate.PAUSED);
        sellerProduct.getItems().forEach(item->{
            Purchase purchaseOverProduct = purchaseRepository.findByShoppingCart(item.getShoppingCart());
            if(purchaseOverProduct!= null && !invoiceRepository.existsByPurchase(purchaseOverProduct)){
                throw new BadRequestException("the publication of this product was paused, but this product cannot be deleted or update, because a purchase was made on it and it is pending approval and invoice");
            }
        });
    }
}
