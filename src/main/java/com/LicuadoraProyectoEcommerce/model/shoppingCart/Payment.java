package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShoppingCart shoppingCart;
//    @NotNull(message = "cant be empty, null or a payment method not allowed for the store")
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String numberOfTransaction;
    public Payment(ShoppingCart shoppingCart, PaymentMethod paymentMethod, String numberOfTransaction){
        this.shoppingCart= shoppingCart;
        this.paymentMethod = paymentMethod;
        this.numberOfTransaction = numberOfTransaction;
    }
//    public void setPaymentMethod(PaymentMethod paymentMethod) {
//        if(shoppingCart.getProductOrders().stream().anyMatch(p-> p.getSellerProduct().getStore().getPaymentMethods().contains(paymentMethod))) this.paymentMethod=null;
//        this.paymentMethod = paymentMethod;
//    }
}
