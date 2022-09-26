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
    public ShoppingCart(){this.orderProducts = new ArrayList<>();
    }

    public Double getFinalPrice() {
        orderProducts.stream().forEach(orderProduct -> {
              this.finalPrice += orderProduct.getFinalPricePerQuantity();});
        return finalPrice;
    }
}
