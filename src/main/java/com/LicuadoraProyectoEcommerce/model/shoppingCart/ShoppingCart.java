package com.LicuadoraProyectoEcommerce.model.shoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @AllArgsConstructor
public class ShoppingCart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buyerName;
    private String buyerEmail;
    private String buyerDni;
    @Transient
    private Double finalPrice;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;
    public ShoppingCart(){
        this.productOrders = new ArrayList<>();
        this.finalPrice =  0d;
    }
    public ShoppingCart(String buyerName, String buyerEmail, String buyerDni){
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerDni = buyerDni;
    }

    public Double getFinalPrice() {
        if(productOrders ==null) return 0d;
        productOrders.stream().forEach(orderProduct -> {
              finalPrice += orderProduct.getFinalPricePerQuantity();});
        return finalPrice;
    }
}
