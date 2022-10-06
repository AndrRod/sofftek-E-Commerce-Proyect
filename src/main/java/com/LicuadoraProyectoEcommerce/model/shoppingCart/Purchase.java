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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String numberOfTransaction;

    @Enumerated(value = EnumType.STRING)
    private StatusPayment status;
    public Purchase(ShoppingCart shoppingCart, PaymentMethod paymentMethod, String numberOfTransaction){
        this.shoppingCart= shoppingCart;
        this.paymentMethod = paymentMethod;
        this.numberOfTransaction = numberOfTransaction;
        this.status = StatusPayment.PENDING;
    }
    @PrePersist
    public void hiddenShoppingCart(){
        this.getShoppingCart().setShowEntity(false);
    }
    @PreRemove
    public void showShoppingCart(){
        this.getShoppingCart().setShowEntity(true);
    }

}
