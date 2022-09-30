package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Payment payment;
    @Transient
    private StatusPayment status;
    private String invoiceNumber;

    public Invoice(Payment payment, String invoiceNumber){
        this.payment= payment;
        this.invoiceNumber = invoiceNumber;
    }

    public void setStatus() {
        if(invoiceNumber==null) this.status=StatusPayment.REJECTED;
        this.status = StatusPayment.ACCEPTED;
    }
}
