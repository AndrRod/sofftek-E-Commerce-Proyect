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
    private String paymentMethod;
    @Transient
    private Double finalPrice;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;
    public ShoppingCart(){
        this.orderProducts = new ArrayList<>();
    }
    public ShoppingCart(String buyerName, String buyerEmail, String buyerDni){
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerDni = buyerDni;
    }

    public Double getFinalPrice() {
        if(orderProducts!=null) orderProducts.stream().forEach(orderProduct -> {
              this.finalPrice += orderProduct.getFinalPricePerQuantity();});
        return finalPrice;
    }
}
