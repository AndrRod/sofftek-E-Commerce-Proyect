package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PaymentDto {
    private Long id;
    private PaymentMethod paymentMethod;
    private String numberOfTransaction;
    private Double totalPurchase;
    private List<Map<String, Double>> products = new ArrayList<>();
    public PaymentDto(Long id, PaymentMethod paymentMethod, String numberOfTransaction, ShoppingCart shoppingCart){
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.numberOfTransaction = numberOfTransaction;
        this.totalPurchase= shoppingCart.getFinalPrice();
        shoppingCart.getProductOrders().stream().forEach(p->{
            this.products.add(Map.of(p.getSellerProduct().getBaseProduct().getName(), p.getFinalPricePerQuantity()));
        });
    }
}
