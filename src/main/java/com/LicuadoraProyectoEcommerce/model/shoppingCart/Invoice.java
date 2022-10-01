package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
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

    public Invoice(Payment payment, String invoiceNumber, StatusPayment status){
        this.payment= payment;
        this.invoiceNumber = invoiceNumber;
        this.status= status;
        this.storeName= this.payment.getStoreName();
        this.buyerDni= this.payment.getBuyerDni();
        this.buyerEmail=this.payment.getBuyerEmail();
        this.buyerName= this.payment.getBuyerName();
        this.paymentMethod = this.payment.getPaymentMethod();
        this.numberOfTransaction = this.payment.getNumberOfTransaction();
        this.totalPurchase= this.payment.getTotalPurchase();
        this.buyerProducts.addAll(this.payment.getProducts());
    }
    public void setBuyerProducts() {
        this.buyerProducts.clear();
        this.buyerProducts.addAll(this.payment.getProducts());
    }
}
