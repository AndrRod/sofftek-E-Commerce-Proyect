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
    private String invoiceNumber;
    public Invoice(Purchase purchase, String invoiceNumber){
        this.purchase = purchase;
        this.invoiceNumber = invoiceNumber;
    }
}
