package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    private String storeName;
    private String buyerName;
    private String buyerEmail;
    private String buyerDni;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String numberOfTransaction;
    private Double totalPurchase;
    @ElementCollection
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "payment_id"))
    private List<String> products = new ArrayList<>();
    public Payment(ShoppingCart shoppingCart, PaymentMethod paymentMethod, String numberOfTransaction){
        this.shoppingCart= shoppingCart;
        this.paymentMethod = paymentMethod;
        this.storeName= this.shoppingCart.getProductOrders().get(0).getSellerProduct().getStore().getName();
        this.buyerDni= shoppingCart.getBuyerDni();
        this.buyerEmail=shoppingCart.getBuyerEmail();
        this.buyerName= shoppingCart.getBuyerName();
        this.numberOfTransaction = numberOfTransaction;
        this.totalPurchase= shoppingCart.getFinalPrice();
        shoppingCart.getProductOrders().stream().forEach(p->{
            this.products.add("Name: "+ p.getSellerProduct().getBaseProduct().getName()+
                    ", Description: " + p.getSellerProduct().getDescription() +
                    ", Amount: " + p.getQuantityOfProducts() +
                    ", Final price: " + p.getFinalPricePerQuantity());
        });
    }
}