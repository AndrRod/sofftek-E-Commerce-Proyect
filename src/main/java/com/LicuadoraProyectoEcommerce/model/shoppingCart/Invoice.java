package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private Purchase purchase;
    @Enumerated(value = EnumType.STRING)
    private StatusPayment status;
    private String storeName;
    private String invoiceNumber;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String numberOfTransaction;
    private Double totalPurchase;
    @ElementCollection
    @CollectionTable(name = "products_invoice", joinColumns = @JoinColumn(name = "invoice_id"))
    private List<String> buyerProducts = new ArrayList<>();
    private String buyerName;
    private String buyerEmail;
    private String buyerDni;

    public Invoice(Purchase purchase, String invoiceNumber){
        this.purchase = purchase;
        this.invoiceNumber = invoiceNumber;
        this.status= this.purchase.getStatus();
        this.storeName= this.purchase.getStoreName();
        this.buyerDni= this.purchase.getBuyerDni();
        this.buyerEmail=this.purchase.getBuyerEmail();
        this.buyerName= this.purchase.getBuyerName();
        this.paymentMethod = this.purchase.getPaymentMethod();
        this.numberOfTransaction = this.purchase.getNumberOfTransaction();
        this.totalPurchase= this.purchase.getTotalPurchase();
        this.buyerProducts.addAll(this.purchase.getProducts());
    }
    public void setBuyerProducts() {
        this.buyerProducts.clear();
        this.buyerProducts.addAll(this.purchase.getProducts());
    }
}
