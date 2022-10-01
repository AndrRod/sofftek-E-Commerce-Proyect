package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("payment method")
    private PaymentMethod paymentMethod;
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @JsonProperty("total purchase")
    private Double totalPurchase;
    private List<String> products = new ArrayList<>();
}
